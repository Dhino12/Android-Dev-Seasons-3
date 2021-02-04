package com.example.moviecatalog.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(
    var titleMovie:String,
    var description: String,
    var dateRelease:String,
    var posterPath: Int,
    var duration:String,
    var director:String,
    var screenPlay:String,
    var yearPublish:String
):Parcelable