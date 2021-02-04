package com.example.moviecatalog.data.source.local.entity.movie_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcomingMovie")
data class UpcomingMovieEntity(
    @ColumnInfo
    var popularity: Double? = null,

    @ColumnInfo
    var voteAverage: Double? = null,

    @PrimaryKey
    @ColumnInfo
    var id: Int? = null,

    @ColumnInfo
    var title: String? = null,

    @ColumnInfo
    var posterPath: String? = null
)