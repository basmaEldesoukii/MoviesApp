package com.banquemisr.movieslist.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.banquemisr.base.BaseViewModel
import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.entity.Movie
import com.banquemisr.movieslist.domain.usecase.GetNowPlayingListUseCase
import com.banquemisr.movieslist.domain.usecase.GetPopularListUseCase
import com.banquemisr.movieslist.domain.usecase.GetUpcomingListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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


    private var moviesList: MoviesListContract.MoviesList = MoviesListContract.MoviesList()

    override fun createInitialState(): MoviesListContract.ListsState {
        return MoviesListContract.ListsState(MoviesListContract.MoviesListState.Loading)
    }

    override fun handleIntent(intent: MoviesListContract.ListsIntent) {
        when (intent) {
            is MoviesListContract.ListsIntent.OnFetchMoviesList -> {
                viewModelScope.launch { updateMovieListState() }
            }
            is MoviesListContract.ListsIntent.OnMovieClicked -> {
                val item = intent.movie
                setSelectedMovie(item)
            }
        }
    }

    private suspend fun getNowPlayingList(){
        getNowPlayingListUseCase.invoke()
            .onStart { emit(Resource.Loading) }
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        setState { copy(moviesListState = MoviesListContract.MoviesListState.Loading) }
                    }
                    is Resource.Success -> {
                        moviesList = moviesList.copy(nowPlayingListData = it.data)
                    }
                    is Resource.Error -> {
                        setState { copy(moviesListState = MoviesListContract.MoviesListState.Error(it.exception.message)) }
                    }
                    else -> {}
                }
            }
    }

    private suspend fun getPopularList(){
        getPopularListUseCase.invoke()
            .onStart { emit(Resource.Loading) }
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        setState { copy(moviesListState = MoviesListContract.MoviesListState.Loading) }
                    }
                    is Resource.Success -> {
                        moviesList = moviesList.copy(popularListData = it.data)
                    }
                    is Resource.Error -> {
                        setState { copy(moviesListState = MoviesListContract.MoviesListState.Error(it.exception.message)) }
                    }
                    else -> {}
                }
            }
    }

    private suspend fun getUpcomingList(){
        getUpcomingListUseCase.invoke()
            .onStart { emit(Resource.Loading) }
            .collect {
                when (it) {
                    is Resource.Loading -> {
                        setState { copy(moviesListState = MoviesListContract.MoviesListState.Loading) }
                    }
                    is Resource.Success -> {
                        moviesList = moviesList.copy(upcomingListData = it.data)
                    }
                    is Resource.Error -> {
                        setState { copy(moviesListState = MoviesListContract.MoviesListState.Error(it.exception.message)) }
                    }
                    else -> {}
                }
            }
    }


    private suspend fun updateMovieListState() = withContext(Dispatchers.IO) {
        val tasks = listOf(
            async(Dispatchers.IO) { getNowPlayingList() },
            async(Dispatchers.IO) { getPopularList() },
            async(Dispatchers.IO) { getUpcomingList() },
        )
        tasks.awaitAll()
        setState { copy( moviesListState = MoviesListContract.MoviesListState.Success(moviesList)) }
    }

    private fun setSelectedMovie(movie: Movie) {
        setState { copy(selectedMovie = movie) }
    }

}