package com.example.moviecatalog.injection

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import com.example.moviecatalog.utils.ApiHelper

object Injection {
    fun providerRepository(lifecycleOwner: LifecycleOwner, context:Context):FilmRepository{
        val remoteRepository = RemoteDataSource.getInstance(lifecycleOwner = lifecycleOwner,ApiHelper(context))

        return FilmRepository.getInstanceFilm(remoteRepository)
    }
}