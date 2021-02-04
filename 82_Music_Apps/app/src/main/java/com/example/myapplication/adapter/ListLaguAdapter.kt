package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.model.ModelLaguList
import kotlinx.android.synthetic.main.item_list_music.view.*

class ListLaguAdapter : RecyclerView.Adapter<ListLaguAdapter.ListLaguViewHolder>() {

    var item : List<ModelLaguList>? = null
    private var onSelectData: onSelectDatas? = null
    private var mContext:Context? = null

    interface onSelectDatas{
        fun onSelected(modelListLagu: ModelLaguList)
    }

    fun ListLaguAdapter(context: Context?, items: List<ModelLaguList>, xSelectData: onSelectDatas) {
        mContext = context
        item = items
        onSelectData = xSelectData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListLaguViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_music,parent,false)
        return ListLaguViewHolder(view)
    }

    override fun getItemCount(): Int = item!!.size

    override fun onBindViewHolder(holder: ListLaguViewHolder, position: Int) {
        item?.get(position)?.let { holder.ViewHolder(it) }
    }

    inner class ListLaguViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun ViewHolder(itemsView: ModelLaguList) {
            Glide.with(mContext!!)
                .load(itemsView.strCoverLagu)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_broken_image_24)
                .into(itemView.imgCoverList)
            itemView.tvBandList.text = itemsView.strNamaBand
            itemView.tvTitleMusic.text = itemsView.strJudulMusic
            itemView.cvListMusic.setOnClickListener{
                onSelectData?.onSelected(itemsView)
            }
        }
    }


}