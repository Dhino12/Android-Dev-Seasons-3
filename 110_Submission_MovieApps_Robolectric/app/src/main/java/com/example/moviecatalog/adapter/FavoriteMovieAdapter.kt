package com.example.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.ui.bookmark.MovieFavoriteFragment
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_grid.view.*

class FavoriteMovieAdapter internal constructor(): PagedListAdapter<MovieDetailEntity,FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieDetailEntity>(){
            override fun areItemsTheSame(oldItem: MovieDetailEntity, newItem: MovieDetailEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDetailEntity, newItem: MovieDetailEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie:MovieDetailEntity?){
            with(itemView){
                tv_titleFilms.text = movie?.title
                setOnClickListener {
                    val intent = Intent(context, DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_MOVIE, movie?.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(context)
                    .load(context.getString(R.string.linkPoster) + movie?.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error))
                    .into(img_posterMovie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getSwipeData(swipedPosition: Int):MovieDetailEntity? = getItem(swipedPosition)
}