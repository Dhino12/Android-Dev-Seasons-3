package com.example.moviecatalog.data.source.local.entity.movie_entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class GenreMovieDetailEntity (
    var name:String? = null
)