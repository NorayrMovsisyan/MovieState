package com.example.moviestate.movies.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.moviestate.BuildConfig
import com.example.moviestate.core.api.TmdbApi
import com.example.moviestate.movies.data.model.Movie
import com.example.moviestate.movies.data.model.MovieTrailerResponse
import com.example.moviestate.movies.data.source.MoviesPagingSource
import com.example.moviestate.movies.domain.repo.TmdbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TmdbRepositoryImpl(
    private val api: TmdbApi,
) : TmdbRepository {

    override fun getPopularMoviesPager(): Pager<Int, Movie> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(api, BuildConfig.API_KEY) }
        )
    }

    override fun getMovieTrailer(movieId: Int): Flow<MovieTrailerResponse> = flow {
        emit(api.getMovieTrailer(movieId, BuildConfig.API_KEY))
    }
        .catch { it.printStackTrace() }
        .flowOn(Dispatchers.IO)

}