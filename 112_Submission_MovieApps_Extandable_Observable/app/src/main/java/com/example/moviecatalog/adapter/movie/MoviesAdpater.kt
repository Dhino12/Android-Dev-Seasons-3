package com.example.moviecatalog.adapter.movie

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
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_grid.view.*

class MoviesAdapter internal constructor(): PagedListAdapter<MovieEntitys, MoviesAdapter.MovieHolder>(
    DIFF_CALLBACK
) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntitys>(){
            override fun areItemsTheSame(oldItem: MovieEntitys, newItem: MovieEntitys): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntitys, newItem: MovieEntitys): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid, parent,false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
        fun bind(theMovies: MovieEntitys?){
            with(itemView){
                tv_titleFilms.text = theMovies?.title
                setOnClickListener {
                    val intent = Intent(context, DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_MOVIE, theMovies?.id)
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(context.getString(R.string.linkPoster) + theMovies?.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error))
                    .into(img_posterMovie)
            }
        }
    }
}