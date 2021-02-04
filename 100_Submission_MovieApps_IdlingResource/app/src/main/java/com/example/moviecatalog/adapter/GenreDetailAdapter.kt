package com.example.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.GenreMovieDetailEntity
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreDetailAdapter:RecyclerView.Adapter<GenreDetailAdapter.ViewModel>() {

    private val listGenreDetail = ArrayList<GenreMovieDetailEntity>()

    fun setMovie(movie: List<GenreMovieDetailEntity>?){
            listGenreDetail.clear()
        if (movie != null) {
            listGenreDetail.addAll(movie)
        }
    }

    class ViewModel(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(genreMovie:GenreMovieDetailEntity){
            with(itemView){
                tvGenre.text = genreMovie.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewModel(view)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.bind(listGenreDetail[position])
    }

    override fun getItemCount(): Int = listGenreDetail.size
}