package com.example.moviecatalog.utils

import androidx.room.TypeConverter
import com.example.moviecatalog.data.source.remote.response.GenreMovieDetailResponse
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun countryLangToJson(list: List<GenreMovieDetailResponse?>?): String? {
        if (list == null) return null
        val lang: GenreMovieDetailResponse? = list[0]
        return if (list.isEmpty()) null else Gson().toJson(lang)
    }
}