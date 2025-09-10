package com.example.moviestate.movies.domain.repo

import androidx.paging.Pager
import com.example.moviestate.movies.data.model.Movie
import com.example.moviestate.movies.data.model.MovieTrailerResponse
import com.example.moviestate.movies.data.model.TrailerResponse
import kotlinx.coroutines.flow.Flow

interface TmdbRepository {
    fun getPopularMoviesPager(): Pager<Int, Movie>

    fun getMovieTrailer(movieId: Int): Flow<MovieTrailerResponse>
}