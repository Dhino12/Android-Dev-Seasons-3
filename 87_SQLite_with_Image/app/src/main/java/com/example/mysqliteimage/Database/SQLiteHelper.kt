package com.example.mysqliteimage.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class SQLiteHelper(
    context: Context?, name: String, factory: SQLiteDatabase.CursorFactory?,
    version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    fun queryData(sql:String){
        val database = writableDatabase
        database.execSQL(sql)
    }

    fun insertData(name:String, desc:String, image:ByteArray){
        val database = writableDatabase
        val sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?)"
        val statment = database.compileStatement(sql)
        statment.clearBindings()
        statment.bindString(1,name)
        statment.bindString(2,desc)
        statment.bindBlob(3,image)
        statment.executeInsert()
    }

    fun updateData(name:String, desc:String, image:ByteArray, id:Int){
        val database = writableDatabase
        val sql = "UPDATE FOOD SET name = ?, description = ?, image = ? WHERE id = ?"
        val statement = database.compileStatement(sql)
        statement.bindString(1,name)
        statement.bindString(2,desc)
        statement.bindBlob(3,image)
        statement.bindDouble(4,id.toDouble())

        statement.execute()
        database.close()
    }

    fun deleteData(id:Int){
        val database = writableDatabase
        val sql = "DELETE FROM FOOD WHERE id = ?"
        val statement = database.compileStatement(sql)
        statement.clearBindings()
        statement.bindDouble(1,id.toDouble())

        statement.execute()
        database.close()
    }

    fun getData(sql:String?):Cursor{
        val database = readableDatabase as SQLiteDatabase
        return database.rawQuery(sql, null)
    }

    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}