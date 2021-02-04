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
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_grid.view.*

class TvShowAdapter internal constructor(): PagedListAdapter<TvEntitys,TvShowAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntitys>(){
            override fun areItemsTheSame(oldItem: TvEntitys, newItem: TvEntitys): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntitys, newItem: TvEntitys): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid,parent,false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(TvShow: TvEntitys?){
            with(itemView){

                tv_titleFilms.text = TvShow?.name

                setOnClickListener {
                    val intent = Intent(context,  DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_TV, TvShow?.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w780/" + TvShow?.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error))
                    .into(img_posterMovie)
            }
        }
    }

}