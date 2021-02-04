package com.example.moviecatalog.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.MovieTrailerEntity
import kotlinx.android.synthetic.main.list_item_trailer.view.*

class TrailerMovieAdapter:RecyclerView.Adapter<TrailerMovieAdapter.ViewHolder>() {

    private val listMovieTrailer = ArrayList<MovieTrailerEntity>()

    fun setData(movie:List<MovieTrailerEntity>){
        listMovieTrailer.clear()
        listMovieTrailer.addAll(movie)
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieTrailer:MovieTrailerEntity){
            with(itemView){
                btn_Trailer.text = movieTrailer.name
                btn_Trailer.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.key)
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
        holder.bind(listMovieTrailer[position])
    }

    override fun getItemCount(): Int = listMovieTrailer.size
}