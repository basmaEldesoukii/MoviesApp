package com.banquemisr.movieslist.presentation.viewmodel

import com.banquemisr.base.UiIntent
import com.banquemisr.base.UiState
import com.banquemisr.movieslist.domain.entity.MoviesListDataModel

class MoviesListContract {

    sealed class ListsIntent: UiIntent {
        data object OnFetchNowPlayingList: ListsIntent()
        data object OnFetchPopularList: ListsIntent()
        data object OnFetchUpcomingList: ListsIntent()
        data class OnMovieClicked(val moviesListDataModel: MoviesListDataModel): ListsIntent()
    }

    data class ListsState (val moviesListState: MoviesListState,
                      val selectedMovie: MoviesListDataModel? = null
    ): UiState

    sealed class MoviesListState {
        data object Loading: MoviesListState()
        data class Success(val moviesListData: List<MoviesListDataModel>): MoviesListState()
        data class Error(val errorMsg: String?): MoviesListState()
    }
}