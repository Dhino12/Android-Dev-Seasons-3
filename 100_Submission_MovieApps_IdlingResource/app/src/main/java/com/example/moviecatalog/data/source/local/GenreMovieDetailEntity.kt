package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreMovieDetailEntity (
    val name:String?
):Parcelable