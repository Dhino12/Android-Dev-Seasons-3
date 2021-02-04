package com.example.moviecatalog.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvEntity(
    var titleTv:String,
    var description:String,
    var broadcast_date:String,
    var posterPath: Int,
    var duration:String,
    var writer:String,
    var kreator:String,
    var yearPublish:String
):Parcelable