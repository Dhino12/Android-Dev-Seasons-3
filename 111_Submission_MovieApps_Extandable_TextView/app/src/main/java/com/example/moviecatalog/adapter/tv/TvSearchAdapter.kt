package com.example.moviecatalog.adapter.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvSearchEntity
import com.example.moviecatalog.ui.detail.DetailActivityMovie
import kotlinx.android.synthetic.main.list_item_grid.view.*

class TvSearchAdapter:RecyclerView.Adapter<TvSearchAdapter.ViewHolder>() {

    private val listSearch = ArrayList<TvSearchEntity>()

    fun setSearch(search:List<TvSearchEntity>){
        listSearch.clear()
        listSearch.addAll(search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSearch[position])
    }

    override fun getItemCount(): Int = listSearch.size

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(tvSearch: TvSearchEntity?){
            with(itemView){
                tv_titleFilms.text = tvSearch?.name
                setOnClickListener {
                    val intent = Intent(context, DetailActivityMovie::class.java)
                    intent.putExtra(DetailActivityMovie.EXTRA_KEY_TV, tvSearch?.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(context)
                    .load(context.getString(R.string.linkPoster) + tvSearch?.posterPath.toString())
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_hourglass_bottom_black_18dp).error(R.drawable.ic_error))
                    .into(img_posterMovie)
            }
        }
    }

}