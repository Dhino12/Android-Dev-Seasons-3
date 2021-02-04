package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpcomingTvResponse(

	@field:SerializedName("results")
	val results: List<ResultsItemUpTv?>? = null

)

data class ResultsItemUpTv(

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null

)

