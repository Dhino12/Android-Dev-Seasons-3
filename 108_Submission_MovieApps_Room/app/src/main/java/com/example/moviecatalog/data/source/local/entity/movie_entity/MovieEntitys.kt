package com.example.moviecatalog.data.source.local.entity.movie_entity

import androidx.room.*

@Entity(tableName = "MovieEntitys")
data class MovieEntitys (
    @ColumnInfo
    var releaseDate: String? = null,

    @ColumnInfo
    var popularity: Double? = null,

    @PrimaryKey
    @ColumnInfo
    var id: Int? = null,

    @ColumnInfo
    var title: String? = null,

    @ColumnInfo
    var posterPath: String? = null,

    @ColumnInfo
    var overview: String? = null,
)