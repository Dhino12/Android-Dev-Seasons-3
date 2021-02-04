package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity
import com.example.moviecatalog.data.source.vo.Resource

class TvViewModel(private val filmRepository: FilmRepository) :ViewModel(){

    fun getTvPop(): LiveData<Resource<List<TvEntitys>>> = filmRepository.getAllTv()

    fun getUpcomingTv(): LiveData<Resource<List<UpcomingTvEntity>>> = filmRepository.getUpcomingTv()
}