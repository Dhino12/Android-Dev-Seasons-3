package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieTrailerResponse(

	@field:SerializedName("results")
	val results: List<ResultsTrailerMovie?>? = null
)

data class ResultsTrailerMovie(

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
