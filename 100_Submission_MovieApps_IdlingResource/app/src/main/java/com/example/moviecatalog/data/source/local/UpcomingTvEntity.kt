package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpcomingTvEntity (
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val id: Int? = null,
    val posterPath: String? = null
):Parcelable