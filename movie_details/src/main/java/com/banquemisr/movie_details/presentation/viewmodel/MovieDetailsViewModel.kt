package com.banquemisr.movie_details.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.banquemisr.base.BaseViewModel
import com.banquemisr.common.Resource
import com.banquemisr.movie_details.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : BaseViewModel<MovieDetailsContract.DetailsIntent, MovieDetailsContract.DetailsState>() {


    override fun createInitialState(): MovieDetailsContract.DetailsState {
        return MovieDetailsContract.DetailsState(MovieDetailsContract.MovieDetailsState.Loading)
    }

    override fun handleIntent(intent: MovieDetailsContract.DetailsIntent) {
        when (intent) {
            is MovieDetailsContract.DetailsIntent.OnFetchMovieDetails -> {
                getMovieDetails(intent.id)
            }
        }
    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.invoke(id)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(movieDetailsState = MovieDetailsContract.MovieDetailsState.Loading) }
                        }
                        is Resource.Success -> {
                            setState { copy(movieDetailsState = MovieDetailsContract.MovieDetailsState.Success(it.data)) }
                        }
                        is Resource.Error -> {
                            setState { copy(movieDetailsState = MovieDetailsContract.MovieDetailsState.Error(it.exception.message)) }
                        }
                        else -> {}
                    }
                }

        }
    }
}