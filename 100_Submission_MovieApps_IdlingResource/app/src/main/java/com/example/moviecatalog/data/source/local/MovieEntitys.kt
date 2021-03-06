package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntitys (
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val id: Int? = null,
    val title: String? = null,
    val posterPath: String? = null
):Parcelable