package com.example.moviestate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.moviestate.app.navigation.NavigationRoot
import com.example.moviestate.app.theme.MovieStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieStateTheme {
                NavigationRoot(rememberNavController())
            }
        }
    }
}