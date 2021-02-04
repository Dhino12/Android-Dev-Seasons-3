package com.example.moviecatalog.data.source.remote.response

data class MovieSearchResponse(
	val results: List<ResultsItemSearchMovie>? = null
)

data class ResultsItemSearchMovie(

	val releaseDate: String? = null,

	val popularity: Double? = null,

	val id: Int? = null,

	val title: String? = null,

	val poster_path: String? = null
)

