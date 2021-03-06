package com.example.academies.di

import android.content.Context

import com.example.academies.data.AcademyRepository
import com.examp	 .academies.data.source.remote.RemoteDataSource
import com.example.academies.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}
