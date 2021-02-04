package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_posts.view.*

//Membuat Adapater untuk comment karna akan menampilkan response yang berbeda
class CommentAdapater(private val listComment:ArrayList<CommentResponse>): RecyclerView.Adapter<CommentAdapater.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(commentResponse: CommentResponse){
            with(itemView){
                val text = """
                    postId: ${commentResponse.postId}
                    id: ${commentResponse.id}
                    name: ${commentResponse.name}
                    email: ${commentResponse.email}
                    body: ${commentResponse.body}
                """.trimIndent()
                tv_Text.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =  listComment.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

}