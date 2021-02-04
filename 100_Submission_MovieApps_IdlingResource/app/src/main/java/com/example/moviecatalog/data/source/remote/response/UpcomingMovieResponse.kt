package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpcomingMovieResponse(

	@field:SerializedName("results")
	val results: List<ResultsItemUpMovie?>? = null
)

data class ResultsItemUpMovie(

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)
