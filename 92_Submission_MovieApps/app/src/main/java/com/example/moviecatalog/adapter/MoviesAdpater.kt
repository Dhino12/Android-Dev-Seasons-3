package com.example.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.data.MovieEntity
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_films.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies:List<MovieEntity>?){
        if(movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_films,parent,false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
        fun bind(theMovies:MovieEntity){
            with(itemView){
                tv_titleFilms.text = theMovies.titleMovie
                tv_descMovie.text = theMovies.description
                tv_releaseDate.text = theMovies.dateRelease
                setOnClickListener {
                    val intent = Intent(context, DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_MOVIE, theMovies.titleMovie)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(theMovies.posterPath)
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error))
                    .into(img_posterMovie)
            }

        }
    }
}