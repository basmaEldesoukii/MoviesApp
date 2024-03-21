package com.banquemisr.movieslist.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.usecase.GetNowPlayingListUseCase
import com.banquemisr.movieslist.domain.usecase.GetPopularListUseCase
import com.banquemisr.movieslist.domain.usecase.GetUpcomingListUseCase
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListContract
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListViewModel
import com.banquemisr.movieslist.utils.MainCoroutineRule
import com.banquemisr.movieslist.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class MoviesListViewModelUnitTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getNowPlayingListUseCase: GetNowPlayingListUseCase
    @MockK
    private lateinit var getPopularListUseCase: GetPopularListUseCase
    @MockK
    private lateinit var getUpcomingListUseCase: GetUpcomingListUseCase

    private lateinit var moviesListViewModel: MoviesListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        moviesListViewModel = MoviesListViewModel(
            getNowPlayingListUseCase = getNowPlayingListUseCase,
            getPopularListUseCase = getPopularListUseCase,
            getUpcomingListUseCase = getUpcomingListUseCase
        )
    }

    @Test
    fun test_fetch_movies_list_success() = runBlockingTest {
        val moviesList = TestDataGenerator.generateDummyMoviesList()
        val movies = TestDataGenerator.generateMoviesList()
        val moviesFlow = flowOf(Resource.Success(movies))

        // Given
        coEvery { getNowPlayingListUseCase.invoke() } returns moviesFlow
        coEvery { getPopularListUseCase.invoke() } returns moviesFlow
        coEvery { getUpcomingListUseCase.invoke() } returns moviesFlow

        // When && Assertions
        moviesListViewModel.uiState.test {
            moviesListViewModel.setIntent(MoviesListContract.ListsIntent.OnFetchMoviesList)
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MoviesListContract.ListsState(
                    moviesListState = MoviesListContract.MoviesListState.Loading,
                    selectedMovie = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            Truth.assertThat(expected).isEqualTo(
                MoviesListContract.ListsState(
                    moviesListState = MoviesListContract.MoviesListState.Success(moviesList),
                    selectedMovie = null
                )
            )

            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // Then
        coVerify { getNowPlayingListUseCase.invoke() }
        coVerify { getPopularListUseCase.invoke() }
        coVerify { getUpcomingListUseCase.invoke() }
    }

    @Test
    fun test_fetch_movies_list_fail() = runBlockingTest {
        val moviesErrorFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getNowPlayingListUseCase.invoke() } returns moviesErrorFlow
        coEvery { getPopularListUseCase.invoke() } returns moviesErrorFlow
        coEvery { getUpcomingListUseCase.invoke() } returns moviesErrorFlow

        // When && Assertions (UiState)
        moviesListViewModel.uiState.test {
            // Call method inside of this scope
            moviesListViewModel.setIntent(MoviesListContract.ListsIntent.OnFetchMoviesList)
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MoviesListContract.ListsState(
                    moviesListState = MoviesListContract.MoviesListState.Loading,
                    selectedMovie = null
                )
            )

            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.moviesListState as MoviesListContract.MoviesListState.Error).errorMsg
            Truth.assertThat(expected).isEqualTo(
                MoviesListContract.ListsState(
                    moviesListState = MoviesListContract.MoviesListState.Error("error"),
                    selectedMovie = null
                )
            )
            Truth.assertThat(expectedData).isEqualTo("error")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then
        coVerify { getNowPlayingListUseCase.invoke() }
        coVerify { getPopularListUseCase.invoke() }
        coVerify { getUpcomingListUseCase.invoke() }
    }

    @Test
    fun test_select_movie() = runBlockingTest {
        val movieItem = TestDataGenerator.generateMovieItem(TestDataGenerator.movieId1)

        // Given (no-op)

        // When && Assertions
        moviesListViewModel.uiState.test {
            // Call method inside of this scope
            moviesListViewModel.setIntent(MoviesListContract.ListsIntent.OnMovieClicked(movie = movieItem))
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MoviesListContract.ListsState(
                    moviesListState = MoviesListContract.MoviesListState.Loading,
                    selectedMovie = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = expected.selectedMovie

            Truth.assertThat(expected).isEqualTo(
                MoviesListContract.ListsState(
                    moviesListState = MoviesListContract.MoviesListState.Loading,
                    selectedMovie = movieItem
                )
            )
            Truth.assertThat(expectedData).isEqualTo(movieItem)
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then (no-op)
    }
}