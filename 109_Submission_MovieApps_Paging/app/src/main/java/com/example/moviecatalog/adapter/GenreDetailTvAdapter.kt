package com.example.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.entity.tv_entity.GenreTvDetailEntity
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreDetailTvAdapter:RecyclerView.Adapter<GenreDetailTvAdapter.ViewHolder>() {

    private val listGenreDetailTv = ArrayList<String>()

    fun setData(tv:List<String>){
        listGenreDetailTv.clear()
        listGenreDetailTv.addAll(tv)
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(genreTv: String){
            with(itemView){
                tvGenre.text = genreTv
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGenreDetailTv[position])
    }

    override fun getItemCount(): Int = listGenreDetailTv.size
}