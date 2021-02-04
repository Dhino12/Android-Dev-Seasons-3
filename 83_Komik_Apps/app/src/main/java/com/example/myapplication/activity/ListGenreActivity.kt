package com.example.myapplication.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterListGenre
import com.example.myapplication.model.ModelGenre
import com.example.myapplication.model.ModelKomik
import com.example.myapplication.networking.APIEndPoint
import kotlinx.android.synthetic.main.activity_list_genre.*
import org.json.JSONException
import org.json.JSONObject

class ListGenreActivity : AppCompatActivity() ,AdapterListGenre.onSelectedData{

    var progressDialog:ProgressDialog? = null
    var adapterListGenre:AdapterListGenre? = null
    var modelKomik = ArrayList<ModelKomik>()
    var modelGenre:ModelGenre? = null
    var endPoint:String? = null
    var type:String? = null
    val numberPage = arrayOf(
        "1", "2", "3", "4", "5"
        , "6", "7", "8", "9", "10"
    )
    var page:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_genre)

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Mohon Tunggu")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Sedang Menampilkan....")

        toolbarListGenre.title = ""
        setSupportActionBar(toolbarListGenre)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modelGenre = intent.getSerializableExtra("listGenre") as ModelGenre
        if(modelGenre != null){
            endPoint = modelGenre?.endPoint
            type = modelGenre?.title

            rvListGenreA.setHasFixedSize(true)
            rvListGenreA.layoutManager = LinearLayoutManager(this)
            tvTypeListGenre.text = type

            val adpWisata = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,numberPage)
            adpWisata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spPage.adapter = adpWisata
            spPage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(p0: AdapterView<*>?) { }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val itemDB = parent?.getItemAtPosition(position)
                    page = itemDB.toString()
                    getGenreList()
                }

            }
        }
    }

    fun getGenreList(){
        progressDialog?.show()

        AndroidNetworking.get(APIEndPoint.GENREDETAIL + page)
            .addPathParameter("endpoint",endPoint)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        modelKomik = ArrayList<ModelKomik>()
                        val playerArray = response.getJSONArray("manga_list")
                        for (i in 0 until playerArray.length()){
                            val temp = playerArray.getJSONObject(i)
                            val dataAPI = ModelKomik()
                            dataAPI.title = temp.getString("title")
                            dataAPI.type = temp.getString("type")
                            dataAPI.thumb = temp.getString("thumb")
                            dataAPI.endpoint = temp.getString("endpoint")
                            modelKomik.add(dataAPI)
                            showGenre()
                        }
                    }catch (e:JSONException){
                        e.printStackTrace()
                        Toast.makeText(this@ListGenreActivity,"Gagal Menampilkan Data ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog?.dismiss()
                    Toast.makeText(this@ListGenreActivity,"Tidak Ada Jaringan Internet $anError",Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun showGenre(){
        adapterListGenre = AdapterListGenre(this,this,modelKomik)
        rvListGenreA.adapter = adapterListGenre
        adapterListGenre?.notifyDataSetChanged()
    }

    override fun onSelectData(modelKomik: ModelKomik) {
        val intent = Intent(this@ListGenreActivity,DetailActivity::class.java)
        intent.putExtra("detailGenre",modelKomik)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}