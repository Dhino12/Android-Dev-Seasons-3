package com.example.mynotesapps.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapps.R
import com.example.mynotesapps.database.Note
import com.example.mynotesapps.ui.insert.NoteAddUpdateActivity
import kotlinx.android.synthetic.main.item_note.view.*

class NotePagedListAdapter internal constructor(private val activity:Activity) : PagedListAdapter<Note, NotePagedListAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Note> = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title && oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note){
            with(itemView){
                tv_item_title.text = note.title
                tv_item_description.text = note.description
                tv_item_date.text = note.date
                cv_item_note.setOnClickListener{
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position) as Note)
    }

}