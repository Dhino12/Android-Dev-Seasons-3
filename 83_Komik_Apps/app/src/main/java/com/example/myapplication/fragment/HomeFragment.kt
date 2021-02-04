package com.example.myapplication.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.myapplication.R
import com.example.myapplication.activity.DetailActivity
import com.example.myapplication.activity.DetailPopulerActivity
import com.example.myapplication.adapter.AdapterKomik
import com.example.myapplication.adapter.AdapterSlider
import com.example.myapplication.model.ModelKomik
import com.example.myapplication.model.ModelSlider
import com.example.myapplication.networking.APIEndPoint
import com.github.islamkhsh.CardSliderViewPager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.main_header.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),AdapterKomik.onSelectedData{

    var rvTerbaru:RecyclerView? = null
    var adapterSlider:AdapterSlider? = null
    var adapterKomik:AdapterKomik? = null
    var progressDialog:ProgressDialog? = null
    var modelKomik = ArrayList<ModelKomik>()
    val modelSlider = ArrayList<ModelSlider>()
    var greetText:TextView? = null
    private var spPage: Spinner? = null
    private val numberPage = arrayOf(
        "1", "2", "3", "4", "5"
        , "6", "7", "8", "9", "10"
    )
    var page:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(activity)
        progressDialog!!.setTitle("Mohon Tunggu")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Sedang Menampilkan Data")

        greetText = view.findViewById(R.id.tvGreeting)
        spPage = view.findViewById(R.id.spPageFragmentHome)

        val adpWisata = ArrayAdapter<String>(activity!!,android.R.layout.simple_spinner_item,numberPage)
        adpWisata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPage!!.adapter = adpWisata

        spPage!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val itemDB = parent.getItemAtPosition(pos)
                page = itemDB.toString()
                getKomikTerbaru()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        rvTerbaru = view.findViewById(R.id.rvTerbaru)
        rvTerbaru!!.setHasFixedSize(true)
        rvTerbaru!!.layoutManager = LinearLayoutManager(activity)
        getGreeting()
        getImageSlider()
    }

    private fun getGreeting(){
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        if(timeOfDay >= 0 && timeOfDay < 12){
            greetText?.text = "Selamat Pagi"
        }else if(timeOfDay >= 12 && timeOfDay < 15){
            greetText?.text = "Selamat Siang"
        }else if(timeOfDay >= 15 && timeOfDay < 18){
            greetText?.text = "Selamat Sore"
        }else{
            greetText?.text = "Selamat Malam"
        }
    }

    private fun getImageSlider(){
        progressDialog?.show()

        AndroidNetworking.get(APIEndPoint.RECOMENDED)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try{
                        progressDialog?.dismiss()
                        val playerArray = response.getJSONArray("manga_list")

                        for(i in 0..7){
                            val temp = playerArray.getJSONObject(i)
                            val dataAPI = ModelSlider()
                            dataAPI.setThumb(temp?.getString("thumb"))
                            modelSlider.add(dataAPI)
                            adapterSlider = AdapterSlider(modelSlider)
                        }
                        viewPagerHeader.adapter = AdapterSlider(modelSlider)
                        Toast.makeText(activity,"Tampil : ${modelSlider.size}",Toast.LENGTH_LONG).show()
                    }catch (e:JSONException){
                        e.printStackTrace()
                        Toast.makeText(activity,"Gagal Menampilkan Data ImageSliider ${e.message}",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog?.dismiss()
                    Toast.makeText(activity,"Tidak Ada Jaringan Internet",Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun getKomikTerbaru(){
        progressDialog?.show()
        AndroidNetworking.get(APIEndPoint.BASE_URL + page)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        modelKomik = ArrayList()
                        val playerArray = response.getJSONArray("manga_list")

                        for(i in 0 until playerArray.length()){
                            val temp = playerArray.getJSONObject(i)
                            val dataAPI = ModelKomik()
                            dataAPI.title = temp.getString("title")
                            dataAPI.thumb = temp.getString("thumb")
                            dataAPI.type = temp.getString("type")
                            dataAPI.update = temp.getString("updated_on")
                            dataAPI.endpoint = temp.getString("endpoint")
                            dataAPI.chapter = temp.getString("chapter")
                            modelKomik.add(dataAPI)
                            showKomikTerbaru()
                        }
                    }catch (e:JSONException){
                        e.printStackTrace()
                        Toast.makeText(activity,"Gagal Menampilkan Data Komik ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog?.dismiss()
                    Toast.makeText(activity,"Tidak Ada Jaringan Internet",Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun showKomikTerbaru(){
        adapterKomik = AdapterKomik(activity!!,modelKomik,this)
        rvTerbaru?.adapter = adapterKomik
        adapterKomik?.notifyDataSetChanged()
    }

    override fun onSelected(modelKomik: ModelKomik) {
        val intent = Intent(activity!!,DetailPopulerActivity::class.java)
        intent.putExtra("detailKomik",modelKomik)
        startActivity(intent)
    }
}













