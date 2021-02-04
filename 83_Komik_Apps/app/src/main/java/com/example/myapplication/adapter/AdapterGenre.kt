package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ModelGenre

class AdapterGenre(private val items:List<ModelGenre>,private val xSelectedData:onSelectedData):RecyclerView.Adapter<AdapterGenre.ViewHolder>() {

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val llgenre = itemView.findViewById<LinearLayout>(R.id.llGenre)
        val tvGenre = itemView.findViewById<TextView>(R.id.tvGenre)
    }

    interface onSelectedData{
        fun onSelected(modelGenre: ModelGenre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_genre,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.tvGenre.text = data.title
        holder.llgenre.setOnClickListener {
            xSelectedData.onSelected(data)
        }
    }
}