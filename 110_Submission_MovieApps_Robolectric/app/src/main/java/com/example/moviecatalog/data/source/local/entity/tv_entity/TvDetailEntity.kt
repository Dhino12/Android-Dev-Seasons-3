package com.example.moviecatalog.data.source.local.entity.tv_entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "tvDetailEntity",
    primaryKeys = ["id"],
    indices = [ Index(value = ["id"]) ]
)
data class TvDetailEntity (

    @ColumnInfo
    val firstAirDate: String? = null,

    @ColumnInfo
    val overview: String? = null,

    @ColumnInfo
    val posterPath: String? = null,

    @ColumnInfo
    val originalName: String? = null,

    @ColumnInfo
    val popularity: Double? = null,

    @ColumnInfo
    val id: Int? = null,

    @ColumnInfo
    var name_genre:String? = null,

    @ColumnInfo
    var favorit:Boolean = false
):Parcelable