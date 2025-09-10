package com.example.moviestate.movies.data.source


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviestate.core.api.TmdbApi
import com.example.moviestate.movies.data.model.Movie

class MoviesPagingSource(
    private val api: TmdbApi,
    private val apiKey: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = api.getPopularMovies(apiKey = apiKey, page = page)
            val movies = response.movies?.filterNotNull() ?: emptyList()
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}
