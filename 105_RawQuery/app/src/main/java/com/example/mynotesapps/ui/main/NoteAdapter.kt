package com.example.mynotesapps.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapps.R
import com.example.mynotesapps.database.Note
import com.example.mynotesapps.helper.NoteDiffCallback
import com.example.mynotesapps.ui.insert.NoteAddUpdateActivity
import com.example.mynotesapps.ui.main.NoteAdapter.NoteViewHolder
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter internal constructor(private val activity:Activity): RecyclerView.Adapter<NoteViewHolder>() {

    private val listNote = ArrayList<Note>()

    fun setListNotes(listNotes:List<Note>){
        val diffCallback = NoteDiffCallback(this.listNote, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listNote.clear()
        this.listNote.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size

    inner class NoteViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        fun bind(note:Note){
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
    /*
    *
    * Kode di atas memanggil kelas NoteDiffCallback untuk memeriksa perubahan yang ada pada listNotes.
    * Jadi jika ada perubahan pada listNotes, maka akan memperbarui secara otomatis.
    * NoteDiffCallback digunakan sebagai pengganti notifyDataSetChanged,
    * yang fungsinya sama-sama untuk melakukan pembaharuan item pada RecyclerView.
    *.
     */
}