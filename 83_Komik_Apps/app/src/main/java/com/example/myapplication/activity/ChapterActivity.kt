package com.example.myapplication.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterChapter
import com.example.myapplication.adapter.AdapterImageChapter
import com.example.myapplication.model.ModelChapter
import com.example.myapplication.networking.APIEndPoint
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_chapter.*
import org.json.JSONException
import org.json.JSONObject

class ChapterActivity : AppCompatActivity() {

    var chapterEndpoint:String? = null
    var title:String? = null
    var modelChapter:ModelChapter? = null
    var viewPager:ViewPager? = null
    var progressDialog:ProgressDialog? = null
    val modelchaptersList = ArrayList<ModelChapter>()
    var adapter:AdapterImageChapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Mohon Tunggu")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("sedang menampilkan gambar ")

        toolbarChapter.title = ""
        setSupportActionBar(toolbarChapter)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modelChapter = intent.getSerializableExtra("detailChapter") as ModelChapter

        if(modelChapter != null){
            chapterEndpoint = modelChapter?.chapterEndPoint
            title = modelChapter?.chapterTitle
            tvTitleChapter.text = title
            tvTitleChapter.isSelected =  true

            tvSubtitle.text = title

            viewPager = findViewById(R.id.viewPager)
            btnNext.setOnClickListener {
                val currentItems = viewPager?.currentItem
                viewPager?.setCurrentItem(currentItems!! + 1)
            }
            btnPrev.setOnClickListener {
                val currentItems = viewPager?.currentItem
                viewPager?.setCurrentItem(currentItems!! - 1)
            }
            getChapterImage()
        }
    }

    private fun getChapterImage(){
        progressDialog?.show()

        AndroidNetworking.get(APIEndPoint.CHAPTERURL)
            .addPathParameter("chapter_endpoint",chapterEndpoint)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        val playerArray = response.getJSONArray("chapter_image")
                        for(i in 0 until playerArray.length()){
                            val temp = playerArray.getJSONObject(i)
                            val dataAPI = ModelChapter()
                            dataAPI.chapterImage = temp.getString("chapter_image_link")
                            dataAPI.imageNumber = temp.getString("image_number")

                            tvSubtitle.text = response.getString("title")
                            modelchaptersList.add(dataAPI)
                            setImage()
                        }
                    }catch (e:JSONException){
                        e.printStackTrace()
                        Toast.makeText(this@ChapterActivity,"Gagal Menampilkan Data ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog?.dismiss()
                    Toast.makeText(this@ChapterActivity,"Tidak Ada Internet ${anError?.message}",Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun setImage(){
        adapter = AdapterImageChapter(modelchaptersList,this)
        viewPager?.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId ==  android.R.id.home){
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}