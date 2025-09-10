package com.example.moviestate.movies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieTrailerResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("results")
    val results: List<TrailerResponse?>? = null
)