package com.example.moviestate.core.api

import com.example.moviestate.movies.data.model.MovieResponse
import com.example.moviestate.movies.data.model.MovieTrailerResponse
import com.example.moviestate.movies.data.model.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("3/movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieTrailerResponse
}