package com.example.mynotesapps.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note:Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * from NOTE ORDER BY ID ASC")
    fun getAllNote(): DataSource.Factory<Int, Note>

    // Untuk menambahkan data dummy, tambahkan kode untuk insertAll
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<Note>)
}