package com.example.moviestate.movies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val movies: List<Movie?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)