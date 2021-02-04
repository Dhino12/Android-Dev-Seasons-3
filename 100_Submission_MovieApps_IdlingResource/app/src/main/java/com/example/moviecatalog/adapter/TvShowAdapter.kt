package com.example.moviecatalog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.TvEntitys
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_grid.view.*

class TvShowAdapter :RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {
    private var listTV = ArrayList<TvEntitys?>()

    fun setTV(TV:List<TvEntitys?>){
        if(TV == null) return
        listTV.clear()
        listTV.addAll(TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid,parent,false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(listTV[position])
    }

    override fun getItemCount(): Int = listTV.size

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