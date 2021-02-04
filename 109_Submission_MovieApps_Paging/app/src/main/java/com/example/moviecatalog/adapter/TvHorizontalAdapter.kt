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
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_horizontal.view.*

class TvHorizontalAdapter : RecyclerView.Adapter<TvHorizontalAdapter.ViewHolder>() {

    private val listMovieHorizontal = ArrayList<UpcomingTvEntity>()

    fun setMovie(Tv:List<UpcomingTvEntity>?){
        if(Tv.isNullOrEmpty()) return
        listMovieHorizontal.clear()
        listMovieHorizontal.addAll(Tv)
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
        fun bind(theTV: UpcomingTvEntity){
            with(itemView){
                titleFilms.text = theTV.name

                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w780/" + theTV.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error).transform(RoundedCorners(16)))
                    .into(imgPhotoHor)

                setOnClickListener {
                    val intent = Intent(context,DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_TV, theTV.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}