package com.example.moviestate.movies.data

import com.example.moviestate.movies.data.model.Movie
import com.example.moviestate.movies.data.model.TrailerResponse
import com.example.moviestate.movies.domain.model.MovieInfo
import com.example.moviestate.movies.domain.model.TrailerInfo

fun Movie.toInfo() = MovieInfo(
    backdropPath = "$IMAGE_BASE_URL$backdropPath",
    id = id ?: 0,
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterPath = "$IMAGE_BASE_URL$posterPath",
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    voteAverage = voteAverage ?: 0.0
)

fun TrailerResponse.toInfo() = TrailerInfo(
    id = id.orEmpty(),
    key = key.orEmpty(),
    name = name.orEmpty(),
    site = site.orEmpty(),
    size = size ?: 0,
    type = type.orEmpty()
)


private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"