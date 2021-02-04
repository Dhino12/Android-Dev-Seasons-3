package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvDetailEntity (
    val firstAirDate: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val id: Int? = null
):Parcelable