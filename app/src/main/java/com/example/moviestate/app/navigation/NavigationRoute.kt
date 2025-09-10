package com.example.moviestate.app.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {

    @Serializable
    data object MoviesList : NavigationRoute
}