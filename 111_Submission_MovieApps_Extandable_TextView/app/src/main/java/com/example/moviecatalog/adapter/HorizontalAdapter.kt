package com.example.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_horizontal.view.*

class HorizontalAdapter : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    private val listMovieHorizontal = ArrayList<UpcomingMovieEntity>()
    private val listTvHorizontal = ArrayList<UpcomingTvEntity>()

    fun setMovie(movie: List<UpcomingMovieEntity>? = null, tv: List<UpcomingTvEntity>? = null){
        if(movie.isNullOrEmpty()){
            listTvHorizontal.clear()
            if (tv != null) {
                listTvHorizontal.addAll(tv)
            }
        }else{
            listMovieHorizontal.clear()
            listMovieHorizontal.addAll(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(listMovieHorizontal.isNullOrEmpty()){
            holder.bind(tv = listTvHorizontal[position])
        }else{
            holder.bind(theMovie = listMovieHorizontal[position])
        }
    }

    override fun getItemCount(): Int =
        if(listMovieHorizontal.isNullOrEmpty()) listTvHorizontal.size
        else listMovieHorizontal.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(theMovie: UpcomingMovieEntity? = null, tv: UpcomingTvEntity? = null){
            if(theMovie != null){

                with(itemView){
                    titleFilms.text = theMovie.title
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

            }else{

                with(itemView){
                    titleFilms.text = tv?.name
                    Glide.with(context)
                        .load(context.getString(R.string.linkPoster) + tv?.posterPath.toString())
                        .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error).transform(RoundedCorners(16)))
                        .into(imgPhotoHor)

                    setOnClickListener {
                        val intent = Intent(context,DetailActivityMovie::class.java)
                        intent.putExtra(DetailActivityMovie.EXTRA_KEY_TV, tv?.id)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}