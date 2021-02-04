package com.example.moviecatalog.data.source.local.entity.combined_entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvTrailerEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity

data class TvWithDetail(
    @Embedded
    var mTvDetail:TvDetailEntity,

    @Relation(parentColumn = "id", entityColumn = "id")
    var mTv:List<TvEntitys>,

    @Relation(parentColumn = "id", entityColumn = "id")
    var mTvTrailer:List<TvTrailerEntity>?,

    @Relation(parentColumn = "id", entityColumn = "id")
    var mTvUpcoming:List<UpcomingTvEntity>
)