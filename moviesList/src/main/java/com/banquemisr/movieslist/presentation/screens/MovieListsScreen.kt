package com.banquemisr.movieslist.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.banquemisr.common.ui.ErrorComponent
import com.banquemisr.common.ui.ProgressComponent
import com.banquemisr.movieslist.presentation.components.MovieListSection
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListContract
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieListsScreen(
    navController: NavHostController,
    viewModel: MoviesListViewModel
) {

    viewModel.setIntent(MoviesListContract.ListsIntent.OnFetchMoviesList)
    val currentState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        when (val state = currentState.moviesListState) {
            is MoviesListContract.MoviesListState.Loading -> {
                ProgressComponent()
            }

            is MoviesListContract.MoviesListState.Success -> {
                val moviesList = state.moviesList

                val nowPlayingList = moviesList.nowPlayingListData
                MovieListSection(title = "Now Playing", movies = nowPlayingList , navController)

                val popularList = moviesList.popularListData
                Spacer(modifier = Modifier.height(16.dp))
                MovieListSection(title = "Popular", movies = popularList, navController)

                val upcomingList = moviesList.upcomingListData
                Spacer(modifier = Modifier.height(16.dp))
                MovieListSection(title = "Upcoming", movies = upcomingList, navController)
            }

            is MoviesListContract.MoviesListState.Error -> {
                ErrorComponent(message = state.errorMsg.toString())
            }
        }
    }
}