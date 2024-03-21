package com.banquemisr.challenge05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.banquemisr.challenge05.ui.theme.MoviesAppTheme
import com.banquemisr.movie_details.presentation.viewmodel.MovieDetailsViewModel
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val moviesListViewModel: MoviesListViewModel by viewModels()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                AppNavigation(moviesListViewModel, movieDetailsViewModel)
            }
        }
    }
}