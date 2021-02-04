package com.example.moviecatalog.data.source.local.entity.tv_entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvTrailer")
data class TvTrailerEntity (

    @ColumnInfo
    @PrimaryKey
    val id:Int? = null,

    @ColumnInfo
    val name: String? = null,

    @ColumnInfo
    val key: String? = null
):Parcelable