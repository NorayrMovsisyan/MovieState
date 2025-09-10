package com.example.moviestate.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviestate.movies.domain.model.MovieInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailSheet(
    onDismiss: () -> Unit,
    onPlayClick: (id: Int) -> Unit,
    movie: MovieInfo?,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = movie?.posterPath,
                    contentDescription = movie?.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clickable(onClick = {onPlayClick(movie?.id ?: 0)})
                        .size(100.dp)
                        .clip(CircleShape),
                )

                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Name",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = movie?.title.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Release Date",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = movie?.releaseDate.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Vote Average",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = movie?.voteAverage.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Text(
                text = movie?.overview.orEmpty(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}