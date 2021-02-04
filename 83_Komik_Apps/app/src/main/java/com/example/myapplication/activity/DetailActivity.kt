package com.example.myapplication.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterChapter
import com.example.myapplication.model.ModelChapter
import com.example.myapplication.model.ModelKomik
import com.example.myapplication.networking.APIEndPoint
import kotlinx.android.synthetic.main.activity_detail.*
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity(),AdapterChapter.onSelectedData{

    var toolbar:Toolbar? = null
    var endPoint:String? = null
    var cover:String? = null
    var thumb:String? = null
    var title:String? = null
    var type:String? = null
    var status:String? = null
    var nameAuthor:String? = null
    var synopsis:String? = null
    var modelKomik:ModelKomik? = null
    var adapterChapter:AdapterChapter? = null
    var progressDialog:ProgressDialog? = null
    var modelChapter = ArrayList<ModelChapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Mohon Tunggu")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Sedang Menampilkan Detail")

        toolbar = findViewById<Toolbar>(R.id.toolbarDetail)
        toolbar?.setTitle("")
        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modelKomik = intent.getSerializableExtra("detailGenre") as ModelKomik
        if (modelKomik != null) {
            endPoint = modelKomik?.endpoint
            title = modelKomik?.title
            cover = modelKomik?.thumb
        }
        tvTitleDetail.text = title
        tvNameDetail.text = title

        tvTitleDetail.isSelected = true
        tvNameDetail.isSelected = true
        tvTCDetail.visibility = View.GONE

        Glide.with(this)
            .load(cover)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgCoverDetail)

        rvChapterDetail.setHasFixedSize(true)
        rvChapterDetail.layoutManager = LinearLayoutManager(this)
        getTotalChapter()
    }

    private fun getTotalChapter(){
        progressDialog?.show()

        AndroidNetworking.get(APIEndPoint.DETAILMANGA)
            .addPathParameter("endpoint", endPoint)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        type = response.getString("type")
                        status = response.getString("type")
                        nameAuthor = response.getString("author")
                        synopsis = response.getString("synopsis")
                        thumb = response.getString("thumb")

                        //Get Image ==========================================
                        Glide.with(applicationContext)
                            .load(thumb)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imgPhotoDetail)

                        //Set Text =============================================
                        tvTypeDetail?.text = "Type : $type"
                        tvStatusDetail?.text = "Status : $status"
                        tvNameAuthorDetail?.text = nameAuthor
                        tvSynopsisDetail?.text = synopsis

                        val jsonArray = response.getJSONArray("chapter")
                        for(x in 0 until jsonArray.length()){
                            val jsonObject = jsonArray.getJSONObject(x)
                            val dataAPI = ModelChapter()

                            dataAPI.chapterTitle = jsonObject.getString("chapter_title")
                            dataAPI.chapterEndPoint = jsonObject.getString("chapter_endpoint")
                            modelChapter.add(dataAPI)
                            showAllChapter()
                        }
                    }catch (e:JSONException){
                        e.printStackTrace()
                        Toast.makeText(this@DetailActivity,"Gagal Menampilkan Data Chapter ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    Toast.makeText(this@DetailActivity,"Gagal Menampilkan Data Chapter ${anError?.message}",Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun showAllChapter(){
        adapterChapter = AdapterChapter(modelChapter,this)
        rvChapterDetail?.adapter = adapterChapter
    }

    override fun onSelected(chapterModel: ModelChapter) {
        val intent = Intent(this@DetailActivity,ChapterActivity::class.java)
        intent.putExtra("detailChapter",chapterModel)
        startActivity(intent)
    }

    private fun setWindowFlag(actvity:Activity, bits:Int, on:Boolean){
        val window = actvity.window
        val winParams = window.attributes as WindowManager.LayoutParams
        if(on){
            winParams.flags = winParams.flags or bits
        }else{
            winParams.flags = winParams.flags and bits.inv()
        }
        window.attributes = winParams
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == android.R.id.home){
            this.finish()
            true
        }else{
            super.onOptionsItemSelected(item)
        }
    }
}