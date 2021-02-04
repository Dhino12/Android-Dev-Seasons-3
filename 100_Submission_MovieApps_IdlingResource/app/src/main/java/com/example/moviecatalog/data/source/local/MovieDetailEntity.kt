package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailEntity (

    val overview: String? = null,

    val title: String? = null,

    val posterPath: String? = null,

    val releaseDate: String? = null,

    val popularity: Double? = null,

    val id: Int? = null,

    val original_title: String? = null
):Parcelable