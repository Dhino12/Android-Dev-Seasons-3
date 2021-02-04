package com.example.moviecatalog.data.source.local.entity.movie_entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movieTrailer")
@Parcelize
data class MovieTrailerEntity (

    @ColumnInfo
    @PrimaryKey
    var id:Int? = null,

    @ColumnInfo
    val size: Int? = null,

    @ColumnInfo
    val name: String? = null,

    @ColumnInfo
    val idTrailer: String? = null,

    @ColumnInfo
    val type: String? = null,

    @ColumnInfo
    val key: String? = null

):Parcelable