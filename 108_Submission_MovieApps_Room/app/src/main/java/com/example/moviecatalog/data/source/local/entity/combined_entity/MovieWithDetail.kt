package com.example.moviecatalog.data.source.local.entity.combined_entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieTrailerEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity

data class MovieWithDetail(

    @Embedded
    var mMovieDetail: MovieDetailEntity,

    @Relation(parentColumn = "id",entityColumn = "id")
    var mMovie:List<MovieEntitys>,

    @Relation(parentColumn = "id",entityColumn = "id")
    var mTrailer:List<MovieTrailerEntity>?,

    @Relation(parentColumn = "id",entityColumn = "id")
    var mUpcomingMovie:List<UpcomingMovieEntity>,
)