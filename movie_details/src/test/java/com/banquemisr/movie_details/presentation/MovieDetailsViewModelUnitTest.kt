package com.banquemisr.movie_details.presentation

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.banquemisr.common.Resource
import com.banquemisr.movie_details.domain.usecase.GetMovieDetailsUseCase
import com.banquemisr.movie_details.presentation.viewmodel.MovieDetailsContract
import com.banquemisr.movie_details.presentation.viewmodel.MovieDetailsViewModel
import com.banquemisr.movie_details.utils.MainCoroutineRule
import com.banquemisr.movie_details.utils.TestDataGenerator
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
class MovieDetailsViewModelUnitTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    private lateinit var moviesListViewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        moviesListViewModel = MovieDetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase
        )
    }

    @Test
    fun test_fetch_movie_details_success() = runBlockingTest {
        val movieDetails = TestDataGenerator.generateMovieDetailsItem(TestDataGenerator.movieId1)
        val moviesFlow = flowOf(Resource.Success(movieDetails))

        // Given
        coEvery { getMovieDetailsUseCase.invoke(TestDataGenerator.movieId1) } returns moviesFlow

        // When && Assertions
        moviesListViewModel.uiState.test {
            moviesListViewModel.setIntent(MovieDetailsContract.DetailsIntent.OnFetchMovieDetails(TestDataGenerator.movieId1))
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MovieDetailsContract.DetailsState(
                    movieDetailsState = MovieDetailsContract.MovieDetailsState.Loading
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.movieDetailsState as MovieDetailsContract.MovieDetailsState.Success).moviesDetailsDataModel
            Truth.assertThat(expected).isEqualTo(
                MovieDetailsContract.DetailsState(
                    movieDetailsState = MovieDetailsContract.MovieDetailsState.Success(movieDetails)
                )
            )
            Truth.assertThat(expectedData).isEqualTo(movieDetails)

            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // Then
        coVerify { getMovieDetailsUseCase.invoke(TestDataGenerator.movieId1) }
    }

    @Test
    fun test_fetch_movie_details_fail() = runBlockingTest {
        val moviesErrorFlow = flowOf(Resource.Error(Exception("error")))

        // Given
        coEvery { getMovieDetailsUseCase.invoke(TestDataGenerator.movieId1) } returns moviesErrorFlow

        // When && Assertions (UiState)
        moviesListViewModel.uiState.test {
            // Call method inside of this scope
            moviesListViewModel.setIntent(
                MovieDetailsContract.DetailsIntent.OnFetchMovieDetails(
                TestDataGenerator.movieId1))
            // Expect Resource.Loading from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MovieDetailsContract.DetailsState(
                    movieDetailsState = MovieDetailsContract.MovieDetailsState.Loading
                )
            )

            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.movieDetailsState as MovieDetailsContract.MovieDetailsState.Error).errorMsg
            Truth.assertThat(expected).isEqualTo(
                MovieDetailsContract.DetailsState(
                    movieDetailsState = MovieDetailsContract.MovieDetailsState.Error("error")
                )
            )
            Truth.assertThat(expectedData).isEqualTo("error")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }
        // Then
        coVerify { getMovieDetailsUseCase.invoke(TestDataGenerator.movieId1) }
    }
}