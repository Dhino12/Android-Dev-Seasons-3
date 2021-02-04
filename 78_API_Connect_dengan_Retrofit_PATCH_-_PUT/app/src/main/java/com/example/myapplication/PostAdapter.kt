package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_posts.view.*

class PostAdapter(private val list:ArrayList<PostResponse>):RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        fun bind(postResponse: PostResponse){
            with(itemView){
                //Menggabungkan data
                val text =
                    "userId: ${postResponse.userId}\n" +
                    "id: ${postResponse.id}\n" +
                            "title: ${postResponse.title}" +
                            "text: ${postResponse.text}"
                tv_Text.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent ,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}