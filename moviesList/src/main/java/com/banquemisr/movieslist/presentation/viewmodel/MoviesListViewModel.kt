package com.banquemisr.movieslist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.banquemisr.base.BaseViewModel
import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import com.banquemisr.movieslist.domain.usecase.GetNowPlayingListUseCase
import com.banquemisr.movieslist.domain.usecase.GetPopularListUseCase
import com.banquemisr.movieslist.domain.usecase.GetUpcomingListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getNowPlayingListUseCase: GetNowPlayingListUseCase,
    private val getPopularListUseCase: GetPopularListUseCase,
    private val getUpcomingListUseCase: GetUpcomingListUseCase,
) : BaseViewModel<MoviesListContract.ListsIntent, MoviesListContract.ListsState>()  {

    private val _nowPlayingListState = MutableStateFlow<List<MoviesListDataModel>>(emptyList())
    val nowPlayingListState: StateFlow<List<MoviesListDataModel>> = _nowPlayingListState

    private val _popularListState = MutableStateFlow<List<MoviesListDataModel>>(emptyList())
    val popularListState: StateFlow<List<MoviesListDataModel>> = _popularListState

    private val _upcomingListState = MutableStateFlow<List<MoviesListDataModel>>(emptyList())
    val upcomingListState: StateFlow<List<MoviesListDataModel>> = _upcomingListState

    override fun createInitialState(): MoviesListContract.ListsState {
        return MoviesListContract.ListsState(MoviesListContract.MoviesListState.Loading)
    }

    override fun handleIntent(intent: MoviesListContract.ListsIntent) {
        when (intent) {
            is MoviesListContract.ListsIntent.OnFetchNowPlayingList -> {
                getMovieLists { getNowPlayingListUseCase.invoke() }
            }
            is MoviesListContract.ListsIntent.OnFetchPopularList -> {
                getMovieLists { getPopularListUseCase.invoke() }
            }
            is MoviesListContract.ListsIntent.OnFetchUpcomingList -> {
                getMovieLists { getUpcomingListUseCase.invoke() }
            }
            is MoviesListContract.ListsIntent.OnMovieClicked -> {
                val item = intent.moviesListDataModel
                setSelectedMovie(item)
            }
        }
    }

    private fun getMovieLists(useCase: suspend () -> Flow<Resource<List<MoviesListDataModel>>>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.invoke()
                    .onStart { emit(Resource.Loading) }
                    .collect {
                        when (it) {
                            is Resource.Loading -> {
                                setState { copy(moviesListState = MoviesListContract.MoviesListState.Loading) }
                            }
                            is Resource.Success -> {
                                setState { copy(moviesListState = MoviesListContract.MoviesListState.Success(it.data)) }
                            }
                            is Resource.Error -> {
                                setState { copy(moviesListState = MoviesListContract.MoviesListState.Error(it.exception.message)) }
                            }
                            else -> {}
                        }
                    }
            }
        }
    }

    private fun setSelectedMovie(movie: MoviesListDataModel) {
        setState { copy(selectedMovie = movie) }
    }

}