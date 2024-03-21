package com.banquemisr.movieslist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.banquemisr.movieslist.domain.entity.Movie

@Composable
fun MovieListSection(title: String, movies: List<Movie>, navController: NavHostController) {
    Column {
        Text(text = title, style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie) {
                    navController.navigate(route = "movie_details/${movie.id}")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
