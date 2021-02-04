package com.example.mynotesapps.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mynotesapps.database.Note
import com.example.mynotesapps.repository.NoteRepository

class MainViewModel(application: Application):ViewModel() {

    private val mNoteRepository:NoteRepository = NoteRepository(application)

    fun getAllNote(sort:String):LiveData<PagedList<Note>> = LivePagedListBuilder(mNoteRepository.getAllNotes(sort), 20).build()
}