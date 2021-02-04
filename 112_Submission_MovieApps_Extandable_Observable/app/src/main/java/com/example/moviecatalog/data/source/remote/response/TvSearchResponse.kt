package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvSearchResponse(

	@field:SerializedName("results")
	val results: List<ResultsItemSearchTv>? = null
)

data class ResultsItemSearchTv(

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)
