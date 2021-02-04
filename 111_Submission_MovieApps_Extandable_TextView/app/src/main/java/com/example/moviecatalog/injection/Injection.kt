package com.example.moviecatalog.injection

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.LocalDataSource
import com.example.moviecatalog.data.source.local.room.FilmDatabase
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import com.example.moviecatalog.utils.ApiHelper
import com.example.moviecatalog.utils.AppExecutors

object Injection {
    fun providerRepository(lifecycleOwner: LifecycleOwner, context:Context):FilmRepository{
        val database = FilmDatabase.getInstance(context)

        val remoteRepository = RemoteDataSource.getInstance(lifecycleOwner = lifecycleOwner,ApiHelper(context))

        val localDataSource = LocalDataSource.getInstance(database.filmDao())

        val appExecutors = AppExecutors()

        return FilmRepository.getInstanceFilm(remoteRepository,localDataSource, appExecutors)
    }
}