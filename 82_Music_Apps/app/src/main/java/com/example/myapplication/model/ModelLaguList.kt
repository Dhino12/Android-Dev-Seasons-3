package com.example.myapplication.model

import java.io.Serializable

class ModelLaguList : Serializable{
    var strId : String? = null

    @JvmField
    var strJudulMusic:String? = null

    @JvmField
    var strNamaBand:String? = null

    @JvmField
    var strCoverLagu:String? = null
}