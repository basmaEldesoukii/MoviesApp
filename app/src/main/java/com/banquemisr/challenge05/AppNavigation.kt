package com.banquemisr.challenge05

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.banquemisr.challenge05.Navigation.Args.MOVIE_ID
import com.banquemisr.movie_details.presentation.screens.MovieDetailsScreen
import com.banquemisr.movie_details.presentation.viewmodel.MovieDetailsViewModel
import com.banquemisr.movieslist.presentation.screens.MovieListsScreen
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListViewModel

@Composable
fun AppNavigation(moviesListViewModel: MoviesListViewModel, movieDetailsViewModel: MovieDetailsViewModel){

    val navController = rememberNavController()

    NavHost(
        navController = navController ,
        startDestination = Navigation.Routes.Movies_LIST
    ){

        composable(
            route = Navigation.Routes.Movies_LIST
        ) {
            MovieListsScreen(
                navController = navController,
                viewModel = moviesListViewModel
            )
        }

        composable(
            route = Navigation.Routes.MOVIE_DETAILS,
            arguments = listOf(navArgument(name = MOVIE_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val movieID = requireNotNull(backStackEntry.arguments?.getInt(MOVIE_ID)) { "Movie id is required as an argument" }
            MovieDetailsScreen(
                navController = navController,
                viewModel = movieDetailsViewModel,
                movieId = movieID
            )
        }
    }
}

object Navigation {

    object Args {
        const val MOVIE_ID = "movie_id"
    }

    object Routes {
        const val Movies_LIST = "movies_list"
        const val MOVIE_DETAILS = "movie_details/{$MOVIE_ID}"
    }

}
