package com.example.moviecatalog.data.source.local.entity.movie_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "MovieDetailEntity",
    primaryKeys = ["id"],
    indices = [ Index(value = ["id"]) ]
)
data class MovieDetailEntity(

    @ColumnInfo
    var overview: String? = null,

    @ColumnInfo
    var title: String? = null,

    @ColumnInfo
    var posterPath: String? = null,

    @ColumnInfo
    var releaseDate: String? = null,

    @ColumnInfo
    var popularity: Double? = null,

    @ColumnInfo
    var id: Int? = null,

    @ColumnInfo
    var original_title: String? = null,

    @ColumnInfo
    var name_genre: String? = null,

    @ColumnInfo
    var favorite:Boolean = false
)