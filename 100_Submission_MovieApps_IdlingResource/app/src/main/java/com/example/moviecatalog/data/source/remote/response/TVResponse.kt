package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVResponse(

	@field:SerializedName("results")
	val results: List<ResultsItemTV?>? = null
)

data class ResultsItemTV(

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)
