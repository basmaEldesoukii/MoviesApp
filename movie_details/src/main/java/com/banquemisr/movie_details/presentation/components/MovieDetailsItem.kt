package com.banquemisr.movie_details.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.banquemisr.common.Constants
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel

@Composable
fun MovieDetailsItem(movie: MovieDetailsDataModel){
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        AsyncImage(
            model = "${Constants.BASE_POSTER_IMAGE_URL}${movie.poster_path}",
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.title,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Overview: ${movie.overview}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Adult: ${if (movie.adult) "Yes" else "No"}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Release Date: ${movie.release_date}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Language: ${movie.original_language}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Popularity: ${movie.popularity}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Vote Average: ${movie.vote_average}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Vote Count: ${movie.vote_count}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Revenue: ${movie.revenue}",
            style = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}