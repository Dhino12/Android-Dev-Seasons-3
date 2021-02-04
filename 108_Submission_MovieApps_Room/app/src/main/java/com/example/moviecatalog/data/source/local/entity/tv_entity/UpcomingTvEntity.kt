package com.example.moviecatalog.data.source.local.entity.tv_entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "upComingTv")
data class UpcomingTvEntity (

    @ColumnInfo
    val popularity: Double? = null,

    @ColumnInfo
    val voteAverage: Double? = null,

    @ColumnInfo
    val name: String? = null,

    @ColumnInfo
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo
    val posterPath: String? = null
):Parcelable