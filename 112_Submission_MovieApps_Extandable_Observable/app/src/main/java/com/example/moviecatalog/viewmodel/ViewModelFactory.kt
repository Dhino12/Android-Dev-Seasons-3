package com.example.moviecatalog.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.injection.Injection

class ViewModelFactory private constructor(private val mFilmRepository: FilmRepository): ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var instance:ViewModelFactory? = null

        fun getInstance(lifecycleOwner: LifecycleOwner, context: Context):ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.providerRepository(lifecycleOwner, context))
            }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(DetailTVViewModel::class.java) -> {
                DetailTVViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvViewModel::class.java) -> {
                FavoriteTvViewModel(mFilmRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}