package com.banquemisr.movie_details.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.banquemisr.common.Resource
import com.banquemisr.movie_details.domain.contract.MovieDetailsRepositoryContract
import com.banquemisr.movie_details.domain.usecase.GetMovieDetailsUseCase
import com.banquemisr.movie_details.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MovieDetailsUseCaseUnitTest {
    @MockK
    private lateinit var repository: MovieDetailsRepositoryContract

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase


    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getMovieDetailsUseCase = GetMovieDetailsUseCase(
            movieDetailsRepositoryContract = repository
        )
    }

    @Test
    fun test_get_movie_details_success() = runBlockingTest{
        val movieDetails = TestDataGenerator.generateMovieDetailsItem(TestDataGenerator.movieId1)
        val moviesFlow = flowOf(Resource.Success(movieDetails))

        // Given
        coEvery { repository.getMovieDetails(TestDataGenerator.movieId1) } returns moviesFlow

        // When & Assertions
        val result = getMovieDetailsUseCase.invoke(TestDataGenerator.movieId1)
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(moviesFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getMovieDetails(TestDataGenerator.movieId1) }
    }

    @Test
    fun test_get_movie_details_fail() = runBlockingTest{
        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getMovieDetails(TestDataGenerator.movieId1) } returns errorFlow

        // When & Assertions
        val result = getMovieDetailsUseCase.invoke(TestDataGenerator.movieId1)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getMovieDetails(TestDataGenerator.movieId1) }
    }
}