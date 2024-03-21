package com.banquemisr.movie_details.data

import androidx.test.filters.SmallTest
import com.banquemisr.movie_details.data.remote.MovieDetailsRemoteDataSourceImp
import com.banquemisr.movie_details.data.remote.MovieDetailsRemoteServices
import com.banquemisr.movie_details.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
@SmallTest
class MovieDetailsRemoteDataSourceImpUnitTest {
    @MockK
    private lateinit var remoteServices: MovieDetailsRemoteServices

    private lateinit var remoteDataSource: MovieDetailsRemoteDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = MovieDetailsRemoteDataSourceImp(
            apiService = remoteServices
        )
    }

    @Test
    fun test_get_movie_details_success() = runBlockingTest {
        val movieDetailsResponse = TestDataGenerator.generateMovieDetailsItem(TestDataGenerator.movieId1)

        // Given
        coEvery { remoteServices.getMovieDetails(TestDataGenerator.movieId1) } returns movieDetailsResponse

        // When
        val result = remoteDataSource.getMovieDetails(TestDataGenerator.movieId1)

        // Then
        coVerify { remoteServices.getMovieDetails(TestDataGenerator.movieId1) }

        // Assertion
        val expected = movieDetailsResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_movie_details_fail() = runBlockingTest {
        // Given
        coEvery { remoteServices.getMovieDetails(TestDataGenerator.movieId1) } throws Exception()

        // When
        remoteDataSource.getMovieDetails(TestDataGenerator.movieId1)

        // Then
        coVerify { remoteServices.getMovieDetails(TestDataGenerator.movieId1) }
    }
}