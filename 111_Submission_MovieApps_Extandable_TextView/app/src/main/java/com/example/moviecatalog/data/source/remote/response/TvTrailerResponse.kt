package com.example.moviecatalog.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvTrailerResponse(

	@field:SerializedName("results")
	val results: List<ResultsTrailerTv>? = null
)

data class ResultsTrailerTv(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)
