package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ModelChapter

class AdapterChapter(private val items:List<ModelChapter>,private val xSelectedData:onSelectedData):RecyclerView.Adapter<AdapterChapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val llChapter = itemView.findViewById<LinearLayout>(R.id.llChapter)
        val btnChapter = itemView.findViewById<Button>(R.id.btnChapter)
    }

    interface onSelectedData{
        fun onSelected(chapterModel:ModelChapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_chapter,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.btnChapter.text = data.chapterTitle
        holder.btnChapter.setOnClickListener {
            xSelectedData.onSelected(data)
        }
    }
}