package com.example.moviecatalog.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvTrailerEntity
import kotlinx.android.synthetic.main.list_item_trailer.view.*

class TrailerTvAdapter:RecyclerView.Adapter<TrailerTvAdapter.ViewHolder>() {

    private val listNameTrailer = ArrayList<String>()
    private val listKeyTrailer = ArrayList<String>()

    fun setData(name:List<String>, key:List<String>){
        listNameTrailer.clear()
        listKeyTrailer.clear()
        listNameTrailer.addAll(name)
        listKeyTrailer.addAll(key)
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(nameTrailer: String,keyTrailer:String ){
            with(itemView){
                btn_Trailer.text = nameTrailer
                btn_Trailer.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=$keyTrailer")
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_trailer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNameTrailer[position], listKeyTrailer[position])
    }

    override fun getItemCount(): Int = listNameTrailer.size
}