package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvEntitys(
    val firstAirDate: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val name: String? = null,
    val id: Int? = null,
    val posterPath: String? = null
):Parcelable
