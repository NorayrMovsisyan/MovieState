package com.example.moviestate.movies.ui

import com.example.moviestate.movies.domain.model.MovieInfo

sealed interface MoviesAction {
    data object OnDetailsDismissClick: MoviesAction
    data class OnDetailsShowClick(val movie: MovieInfo): MoviesAction

    data class OnPlayClick(val id: Int): MoviesAction
}