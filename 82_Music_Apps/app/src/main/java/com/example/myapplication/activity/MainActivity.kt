package com.example.myapplication.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.myapplication.R
import com.example.myapplication.adapter.ListLaguAdapter
import com.example.myapplication.model.ModelLaguList
import com.example.myapplication.network.API_LaguAnime
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() , ListLaguAdapter.onSelectDatas{
    private var listLaguAdapter : ListLaguAdapter? = null
    var progressDialog:ProgressDialog? = null
    var modelListLagu:MutableList<ModelLaguList> = ArrayList()

    companion object{
        fun setWindowFlag(activity: Activity,bits:Int,on:Boolean){
            val win = activity.window
            val winParams = win.attributes
            if(on){
                winParams.flags = winParams.flags or bits
            }else{
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set Transparent Status Bar
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    )
        }

        if(Build.VERSION.SDK_INT >= 21){
            setWindowFlag(this,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,false)
        }

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Mohon Tunggu")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Sedang Menampilkan Data.....")

        rvListMusic.setHasFixedSize(true)
        rvListMusic.layoutManager = LinearLayoutManager(this)

        listMusic
    }

    private val listMusic:Unit
    private get(){
        progressDialog!!.show()
        AndroidNetworking.get(API_LaguAnime.ListMusic)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog!!.dismiss()
                        val playerArray = response.getJSONArray("post")
                        for (i in 0 until playerArray.length()){
                            if(i > 3){
                                val temp = playerArray.getJSONObject(i)
                                val dataAPI = ModelLaguList()
                                dataAPI.strId = temp.getString("id")
                                dataAPI.strCoverLagu = temp.getString("coverartikel")
                                dataAPI.strNamaBand = temp.getString("namaband")
                                dataAPI.strJudulMusic = temp.getString("judulmusic")
                                modelListLagu.add(dataAPI)
                                showListMusic()
                            }
                        }
                    }catch (e:JSONException){
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity,"Gagal Menampilkan Data ",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog!!.dismiss()
                    Toast.makeText(this@MainActivity,"Tidak Ada Jaringan $anError",Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onSelected(modelListLagu: ModelLaguList) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("detailLagu",modelListLagu)
        startActivity(intent)
    }

    fun showListMusic(){
        listLaguAdapter = ListLaguAdapter()
        listLaguAdapter?.ListLaguAdapter(this@MainActivity,modelListLagu,this)
        rvListMusic.adapter = listLaguAdapter
    }

}