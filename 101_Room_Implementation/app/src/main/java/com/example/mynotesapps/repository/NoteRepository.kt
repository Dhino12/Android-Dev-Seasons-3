package com.example.mynotesapps.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotesapps.database.Note
import com.example.mynotesapps.database.NoteDao
import com.example.mynotesapps.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDAO :NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNoteDAO = db.noteDAO()
    }

    fun getAllNotes():LiveData<List<Note>> = mNoteDAO.getAllNote()

    fun insert(note: Note){
        executorService.execute{ mNoteDAO.insert(note) }
    }

    fun delete(note:Note){
        executorService.execute { mNoteDAO.delete(note) }
    }

    fun update(note: Note){
        executorService.execute { mNoteDAO.update(note) }
    }
}