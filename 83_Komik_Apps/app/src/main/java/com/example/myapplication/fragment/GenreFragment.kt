package com.example.myapplication.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.myapplication.R
import com.example.myapplication.activity.ListGenreActivity
import com.example.myapplication.adapter.AdapterGenre
import com.example.myapplication.model.ModelGenre
import com.example.myapplication.networking.APIEndPoint
import com.example.myapplication.utils.LayoutMarginDecoration
import com.example.myapplication.utils.Tools
import kotlinx.android.synthetic.main.fragment_genre.*
import org.json.JSONObject
import java.lang.Exception

class GenreFragment : Fragment() ,AdapterGenre.onSelectedData{

    var adapterGenre:AdapterGenre? = null
    var progressDialog:ProgressDialog? = null
    var gridMargin:LayoutMarginDecoration? = null
    val modelGenre = ArrayList<ModelGenre>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(activity)
        progressDialog?.setTitle("Mohon Tunggu")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Sedang Menampilkan Data")

        rvGenreFragment.setHasFixedSize(true)
        val mLayoutManager = GridLayoutManager(activity,3,RecyclerView.VERTICAL,false)
        rvGenreFragment.layoutManager = mLayoutManager
        gridMargin = LayoutMarginDecoration(3, Tools.dp2px(activity!!,12.0F))
        rvGenreFragment.addItemDecoration(gridMargin!!)
        getGenre()
    }

    private fun getGenre(){
        progressDialog?.show()
        AndroidNetworking.get(APIEndPoint.GENREURL)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    try {
                        progressDialog?.dismiss()
                        val playerArray = response.getJSONArray("list_genre")
                        for(i in 0 until playerArray.length()){
                            val temp = playerArray.getJSONObject(i)
                            val dataAPI = ModelGenre()
                            dataAPI.title = temp.getString("title")
                            dataAPI.endPoint = temp.getString("endpoint")
                            modelGenre.add(dataAPI)
                            showGenre()
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                        Toast.makeText(activity,"Gagal Menampilkan Data ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    progressDialog?.dismiss()
                    Toast.makeText(activity,"Gagal Menampilkan Data ${anError?.message}",Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun showGenre(){
        adapterGenre = AdapterGenre(modelGenre,this)
        rvGenreFragment.adapter = adapterGenre
    }

    override fun onSelected(modelGenre: ModelGenre) {
        val intent = Intent(activity,ListGenreActivity::class.java)
        intent.putExtra("listGenre",modelGenre)
        startActivity(intent)
    }
}