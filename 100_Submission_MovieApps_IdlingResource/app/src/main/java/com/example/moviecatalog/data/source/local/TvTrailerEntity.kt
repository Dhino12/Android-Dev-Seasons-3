package com.example.moviecatalog.data.source.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvTrailerEntity (

    val name: String? = null,

    val id: String? = null,

    val type: String? = null,

    val key: String? = null
):Parcelable