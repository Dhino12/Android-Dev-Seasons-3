package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.tv_entity.GenreTvDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvTrailerEntity
import com.example.moviecatalog.data.source.vo.Resource

class DetailTVViewModel(private val filmRepository: FilmRepository):ViewModel() {

    private var tvId = MutableLiveData<Int>()

    fun setSelectedFilms(tvID:Int?){
        this.tvId.value = tvID
    }

    var tvDetail:LiveData<Resource<TvWithDetail>> = Transformations.switchMap(tvId){
        mTvID -> filmRepository.getTvDetail(mTvID)
    }

    fun getTrailer() :LiveData<Resource<TvTrailerEntity>> = Transformations.switchMap(tvId){
        mTvID -> filmRepository.getTrailerTv(mTvID)
    }

    fun setBookmark(){
        val movieResource = tvDetail.value
        if(movieResource != null){

            val movieWithDetail = movieResource.data

            if(movieWithDetail != null){
                val tvEntityDetail = movieWithDetail.mTvDetail
                val newState = !tvEntityDetail.favorit
                filmRepository.setFavoriteTV(tvEntityDetail, newState)
            }
        }
    }
}