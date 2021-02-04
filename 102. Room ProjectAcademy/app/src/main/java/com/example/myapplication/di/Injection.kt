package com.example.myapplication.di

import android.content.Context

import com.example.myapplication.data.AcademyRepository
import com.example.myapplication.data.source.local.LocalDataSource
import com.example.myapplication.data.source.local.room.AcademyDatabase
import com.example.myapplication.data.source.remote.RemoteDataSource
import com.example.myapplication.utils.AppExecutor
import com.example.myapplication.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteRepository = RemoteDataSource.getInstance(JsonHelper(context))

        val localDataSource = LocalDataSource.getInstance(database.academyDao())

        val appExecutor = AppExecutor()

        return AcademyRepository.getInstance(remoteRepository,localDataSource, appExecutor)
    }
}
