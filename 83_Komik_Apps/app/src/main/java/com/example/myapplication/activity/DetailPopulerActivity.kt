package com.example.myapplication.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_list_genre.*
import org.json.JSONException
import org.json.JSONObject

class DetailPopulerActivity:AppCompatActivity(),AdapterChapter.onSelectedData {

    var endpoint:String? = null
    var cover:String? = null
    var thumb:String? = null
    var title:String? = null
    var type:String? = null
    var status:String? = null
    var nameAuthor:String? = null
    var allChapter:String? = null
    var synopsis:String? = null
    var modelKomik:ModelKomik? = null
    var adapterChapter:AdapterChapter? = null
    var progressDialog:ProgressDialog? = null
    var modelChapter = ArrayList<ModelChapter>()

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState )
        setContentView(R.layout.activity_detail)

        if(Build.VERSION.SDK_INT < 21){
            setWindowFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,true)
        }else if(Build.VERSION.SDK_INT >= 19){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN
        }else if(Build.VERSION.SDK_INT >= 21){
            setWindowFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false)
            window.statusBarColor = Color.TRANSPARENT
        }

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Mohon Tunggu")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Sedang Menampilkan Data....")

        toolbarDetail.title = ""
        setSupportActionBar(toolbarDetail)
        assert(supportActionBar != null)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        modelKomik = intent.getSerializableExtra("detailKomik") as ModelKomik

        if(modelKomik != null){
            endpoint = modelKomik?.endpoint
            title = modelKomik?.title
            cover = modelKomik?.thumb
            allChapter = modelKomik?.chapter

            tvTitleDetail.text = title
            tvNameDetail.text = title

            tvTitleDetail.isSelected = true
            tvNameDetail.isSelected = true
            tvAllChapter.text = allChapter

            Glide.with(this)
                .load(cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgCoverDetail)

            rvChapterDetail.setHasFixedSize(true)
            rvChapterDetail.layoutManager = LinearLayoutManager(this)

            getTotalChapter()
        }
    }

    private fun getTotalChapter(){
        progressDialog?.show()

        AndroidNetworking.get(APIEndPoint.DETAILMANGA)
            .addPathParameter("endpoint",endpoint)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        type = response.getString("type")
                        status = response.getString("status")
                        nameAuthor = response.getString("author")
                        synopsis = response.getString("synopsis")
                        thumb = response.getString("thumb")

                        //Get Image
                        Glide.with(applicationContext)
                            .load(thumb)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imgPhotoDetail)

                        tvTypeDetail.text = "Type : $type"
                        tvStatusDetail.text = "Status : $status"
                        tvNameAuthorDetail.text = nameAuthor
                        tvSynopsisDetail.text = synopsis

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
                        Toast.makeText(this@DetailPopulerActivity,"Gagal Menampilkan Data ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog?.dismiss()
                    Toast.makeText(this@DetailPopulerActivity,"Tidak Ada Internet ${anError?.message}",Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun showAllChapter(){
        adapterChapter = AdapterChapter(modelChapter,this)
        rvChapterDetail.adapter = adapterChapter
    }

    override fun onSelected(chapterModel: ModelChapter) {
        val intent = Intent(this@DetailPopulerActivity,ChapterActivity::class.java)
        intent.putExtra("detailChapter",modelChapter)
        startActivity(intent)
    }

    fun setWindowFlag(activity:Activity,bits:Int,on:Boolean){
        val window = activity.window
        val winParams = window.attributes
        if(on){
            winParams.flags or bits
        }else{
            winParams.flags and bits.inv()
        }
        window.attributes = winParams
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}