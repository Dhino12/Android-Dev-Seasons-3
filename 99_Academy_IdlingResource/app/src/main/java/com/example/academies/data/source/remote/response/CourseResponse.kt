package com.example.academies.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parceliz 
@Parcelize
data class CourseResponse(
        var id: String,
        var title: String,
        var description: String,
        var date: String,
        var imagePath: String
) : Parcelable

