package com.example.moviestate.movies.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.moviestate.movies.domain.model.MovieInfo
import com.example.moviestate.movies.ui.components.MovieDetailSheet
import com.example.moviestate.movies.ui.components.MovieExpandableText
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoviesRoot() {

    val viewModel = koinViewModel<MoviesViewModel>()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsStateWithLifecycle()
    MoviesScreen(
        movies = movies,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MoviesScreen(
    movies: LazyPagingItems<MovieInfo>,
    state: MoviesState,
    onAction: (MoviesAction) -> Unit,
) {
    val context = LocalContext.current
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(8.dp),
            ) {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    if (movie != null) {
                        MovieItem(movie) {
                            onAction(MoviesAction.OnDetailsShowClick(it))
                        }
                    }
                }
                item {
                    movies.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                LoadingItem()
                            }

                            loadState.append is LoadState.Loading -> {
                                LoadingItem()
                            }

                            loadState.append is LoadState.Error -> {
                                // item { ErrorItem { retry() } }
                            }
                        }
                    }
                }
            }
        }
        if (state.showDetails) {
            MovieDetailSheet(
                onDismiss = { onAction(MoviesAction.OnDetailsDismissClick) },
                movie = state.selectedMovie,
                onPlayClick = {
                    onAction(MoviesAction.OnPlayClick(it))
                }
            )
        }
        if (state.showTrailer && state.trailerUrl != null) {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = state.trailerUrl.toUri()
            }
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            } finally {
                onAction(MoviesAction.OnDetailsDismissClick)
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieInfo,
    itemClick: (movie: MovieInfo) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { itemClick(movie) }
            .padding(8.dp)) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            clipToBounds = true,
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            MovieExpandableText(text = movie.overview)
        }
    }
}

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}
