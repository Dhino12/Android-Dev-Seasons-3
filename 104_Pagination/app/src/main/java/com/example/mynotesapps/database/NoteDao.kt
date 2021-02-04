package com.example.mynotesapps.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note:Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    // @RawQuery digunakan untuk menandai bahwa fungsi tersebut menggunakan fitur RawQuery,
    // akibatnya Anda harus menambahkan parameter berupa SupportSQLiteQuery.
    // Selain itu supaya data tersebut bisa di-observe ketika ada perubahan data,
    // gunakanlah observedEntities = Note.class.
    @RawQuery(observedEntities = [Note::class])
    fun getAllNote(query:SupportSQLiteQuery): DataSource.Factory<Int, Note>

    // Untuk menambahkan data dummy, tambahkan kode untuk insertAll
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<Note>)
}