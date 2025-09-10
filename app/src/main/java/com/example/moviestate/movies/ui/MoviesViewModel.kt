package com.example.moviestate.movies.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.moviestate.movies.data.toInfo
import com.example.moviestate.movies.domain.repo.TmdbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MoviesViewModel(
    private val moviesRepository: TmdbRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    val movies = moviesRepository.getPopularMoviesPager()
        .flow
        .map { pagingData -> pagingData.map { movie -> movie.toInfo() } }
        .cachedIn(viewModelScope)
        .catch { it.printStackTrace() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    private val _state = MutableStateFlow(MoviesState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                getMovieTrailer()
                hasLoadedInitialData = true
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MoviesState())

    fun onAction(action: MoviesAction) {
        when (action) {
            MoviesAction.OnDetailsDismissClick -> {
                _state.update { it.copy(showDetails = false, selectedMovie = null) }
            }

            is MoviesAction.OnDetailsShowClick -> {
                _state.update { it.copy(showDetails = true, selectedMovie = action.movie) }
            }

            is MoviesAction.OnPlayClick -> {
                _state.update { it.copy(showTrailer = true) }
            }
        }
    }

    private fun getMovieTrailer() {
        state
            .filter { it.selectedMovie != null && it.showDetails }
            .distinctUntilChanged()
            .flatMapConcat {
                moviesRepository.getMovieTrailer(it.selectedMovie?.id ?: 0)
            }
            .map { it.results?.map { it?.toInfo() }?.firstOrNull() }
            .mapNotNull { "https://www.youtube.com/watch?v=${it?.key}" }
            .filterNotNull()
            .onEach { url ->
                _state.update { it.copy(trailerUrl = url) }
            }
            .catch { Log.e("TAG123", "getMovieTrailer: ${it.cause}", ) }
            .launchIn(viewModelScope)
    }

}

