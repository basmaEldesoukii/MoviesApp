package com.banquemisr.movieslist.presentation.viewmodel

import com.banquemisr.base.UiIntent
import com.banquemisr.base.UiState
import com.banquemisr.movieslist.domain.entity.Movie

class MoviesListContract {

    sealed class ListsIntent: UiIntent {
        data object OnFetchMoviesList: ListsIntent()
        data class OnMovieClicked(val movie: Movie): ListsIntent()
    }

    data class ListsState (val moviesListState: MoviesListState,
                      val selectedMovie: Movie? = null
    ): UiState

    sealed class MoviesListState {
        data object Loading: MoviesListState()
        data class Success(
            val moviesList: MoviesList
        ): MoviesListState()
        data class Error(val errorMsg: String?): MoviesListState()
    }

    data class MoviesList(
        val nowPlayingListData: List<Movie> = emptyList(),
        val popularListData: List<Movie> = emptyList(),
        val upcomingListData: List<Movie> = emptyList()
    )
}