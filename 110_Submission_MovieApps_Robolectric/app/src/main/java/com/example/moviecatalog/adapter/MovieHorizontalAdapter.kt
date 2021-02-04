package com.example.moviecatalog.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_horizontal.view.*

class MovieHorizontalAdapter : RecyclerView.Adapter<MovieHorizontalAdapter.ViewHolder>() {

    private val listMovieHorizontal = ArrayList<UpcomingMovieEntity>()

    fun setMovie(movie: List<UpcomingMovieEntity>){
        listMovieHorizontal.clear()
        listMovieHorizontal.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovieHorizontal[position])
    }

    override fun getItemCount(): Int = listMovieHorizontal.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(theMovie: UpcomingMovieEntity){
            with(itemView){
                titleFilms.text = theMovie.title
                Log.e("errorAdapterUpCom",theMovie.title.toString())
                Log.e("errorAdapterUpCom",theMovie.id.toString())
                Glide.with(context)
                    .load(context.getString(R.string.linkPoster) + theMovie.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error).transform(RoundedCorners(16)))
                    .into(imgPhotoHor)

                setOnClickListener {
                    val intent = Intent(context,DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_MOVIE, theMovie.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}