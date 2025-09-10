package com.example.moviestate.movies.di

import com.example.moviestate.movies.data.repo.TmdbRepositoryImpl
import com.example.moviestate.movies.domain.repo.TmdbRepository
import com.example.moviestate.movies.ui.MoviesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moviesModule = module {
    singleOf(::TmdbRepositoryImpl) bind TmdbRepository::class
    viewModelOf(::MoviesViewModel)
}