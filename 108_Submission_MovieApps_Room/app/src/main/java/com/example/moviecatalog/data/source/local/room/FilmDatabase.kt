package com.example.moviecatalog.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieTrailerEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvTrailerEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity

@Database(
    entities = [
        MovieEntitys::class,
        MovieDetailEntity::class,
        MovieTrailerEntity::class,
        UpcomingMovieEntity::class,
        TvEntitys::class,
        TvDetailEntity::class,
        TvTrailerEntity::class,
        UpcomingTvEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase :RoomDatabase() {
    abstract fun filmDao():FilmDao

    companion object{
        @Volatile
        private var INSTANCE:FilmDatabase? = null
        fun getInstance(context: Context):FilmDatabase =
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                FilmDatabase::class.java,
                "FilmDB.db"
            ).build()
    }
}