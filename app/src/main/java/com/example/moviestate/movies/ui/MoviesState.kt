package com.example.moviestate.movies.ui

import com.example.moviestate.movies.domain.model.MovieInfo

data class MoviesState(
    val showDetails: Boolean = false,
    val selectedMovie: MovieInfo? = null,
    val trailerUrl: String? = null,
    val showTrailer: Boolean = false
)
