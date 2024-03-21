package com.banquemisr.movie_details.presentation.viewmodel

import com.banquemisr.base.UiIntent
import com.banquemisr.base.UiState
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel

class MovieDetailsContract {
    sealed class DetailsIntent: UiIntent {
        data class OnFetchMovieDetails(val id: Int): DetailsIntent()
    }

    data class DetailsState(val movieDetailsState: MovieDetailsState) : UiState

    sealed class MovieDetailsState {
        data object Loading: MovieDetailsState()
        data class Success(val moviesDetailsDataModel: MovieDetailsDataModel): MovieDetailsState()
        data class Error(val errorMsg: String?): MovieDetailsState()
    }
}