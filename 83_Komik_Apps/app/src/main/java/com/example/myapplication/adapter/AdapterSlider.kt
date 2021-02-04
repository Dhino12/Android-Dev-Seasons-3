package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R
import com.example.myapplication.model.ModelSlider
import com.github.islamkhsh.CardSliderAdapter

class AdapterSlider(private val movies:List<ModelSlider>): CardSliderAdapter<AdapterSlider.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val imgSlider = itemView.findViewById<ImageView>(R.id.imgSlider)
    }

    override fun bindVH(holder: MovieViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(movies[position].getThumb())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(Target.SIZE_ORIGINAL)
            .into(holder.imgSlider)
    }

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_slider,parent,false)
        return MovieViewHolder(view)
    }
}