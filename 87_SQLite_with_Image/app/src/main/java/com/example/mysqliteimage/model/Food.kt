package com.example.mysqliteimage.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Food(
    val id:Int,
    val name:String,
    val price:String,
    val image:ByteArray
): Parcelable