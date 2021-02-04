package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @field:SerializedName("overview")
	val overview: String? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("poster_path")
	val posterPath: String? = null,

    @field:SerializedName("release_date")
	val releaseDate: String? = null,

    @field:SerializedName("popularity")
	val popularity: Double? = null,

    @field:SerializedName("genres")
    val genre: List<GenreMovieDetailResponse?>? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("original_title")
    val original_title: String? = null
)

data class GenreMovieDetailResponse(
    @field:SerializedName("name")
    val name:String? = null
)