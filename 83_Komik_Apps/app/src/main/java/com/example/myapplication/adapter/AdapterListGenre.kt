package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.example.myapplication.R
import com.example.myapplication.model.ModelKomik

class AdapterListGenre(private val mContext:Context, private val onSelected:onSelectedData, private val items:List<ModelKomik>):RecyclerView.Adapter<AdapterListGenre.ViewHolder>() {

    interface onSelectedData{
        fun onSelectData(modelKomik:ModelKomik)
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val tvTitle:TextView = itemView.findViewById(R.id.tvTitleKomik)
        val tvType:TextView = itemView.findViewById(R.id.tvTypeKomik)
        val cardView:CardView = itemView.findViewById(R.id.cvTerbaru)
        val imgPhoto:ImageView = itemView.findViewById(R.id.imgPhotoKomik)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_komik,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        Glide.with(mContext)
            .load(data.thumb)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(Target.SIZE_ORIGINAL)
            .into(holder.imgPhoto)

        holder.tvTitle.text = data.title
        holder.tvType.text = data.type
        holder.cardView.setOnClickListener {
            onSelected.onSelectData(data)
        }
    }
}