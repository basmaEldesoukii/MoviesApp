package com.banquemisr.movie_details.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.banquemisr.common.ui.ErrorComponent
import com.banquemisr.common.ui.ProgressComponent
import com.banquemisr.movie_details.presentation.components.MovieDetailsItem
import com.banquemisr.movie_details.presentation.viewmodel.MovieDetailsContract
import com.banquemisr.movie_details.presentation.viewmodel.MovieDetailsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    navController: NavController,
    viewModel: MovieDetailsViewModel,
    movieId: Int
) {

    viewModel.setIntent(MovieDetailsContract.DetailsIntent.OnFetchMovieDetails(movieId))

    val currentState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back to movies list")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (val state = currentState.movieDetailsState) {
                is MovieDetailsContract.MovieDetailsState.Loading -> {
                    ProgressComponent()
                }

                is MovieDetailsContract.MovieDetailsState.Success -> {
                    val movie = state.moviesDetailsDataModel
                    MovieDetailsItem(movie = movie)
                }

                is MovieDetailsContract.MovieDetailsState.Error -> {
                    ErrorComponent(message = state.errorMsg.toString())
                }
            }
        }
    }
}
