package com.example.moviestate.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviestate.movies.ui.MoviesRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavigationRoute.MoviesList){
        composable<NavigationRoute.MoviesList> {
            MoviesRoot()
        }
    }
}