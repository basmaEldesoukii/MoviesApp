package com.banquemisr.movie_details.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.banquemisr.common.Resource
import com.banquemisr.movie_details.data.local.MovieDetailsLocalDataSourceContract
import com.banquemisr.movie_details.data.remote.MovieDetailsRemoteDataSourceContract
import com.banquemisr.movie_details.data.repo.MovieDetailsDataMapper
import com.banquemisr.movie_details.data.repo.MovieDetailsRepositoryImp
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
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class MovieDetailsRepositoryImpUnitTest {
    @MockK
    private lateinit var localDataSourceContract: MovieDetailsLocalDataSourceContract

    @MockK
    private lateinit var remoteDataSourceContract: MovieDetailsRemoteDataSourceContract

    private val movieDetailsDataMapper = MovieDetailsDataMapper()

    private lateinit var repository: MovieDetailsRepositoryImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = MovieDetailsRepositoryImp(
            localDataSource = localDataSourceContract,
            remoteDataSource = remoteDataSourceContract,
            movieDetailsDataMapper = movieDetailsDataMapper
        )
    }

    @Test
    fun test_get_movie_details_from_remote_success() = runBlockingTest{
        val movieDetails = TestDataGenerator.generateMovieDetailsItem(TestDataGenerator.movieId1)
        val affectedIds = 1L

        // Given
        coEvery { remoteDataSourceContract.getMovieDetails(TestDataGenerator.movieId1) } returns movieDetails
        coEvery { localDataSourceContract.insertMovieDetails(movieDetailsDataMapper.from(movieDetails)) } returns affectedIds
        coEvery { localDataSourceContract.getMovieDetailsByIdFromDataBase(TestDataGenerator.movieId1) } returns movieDetailsDataMapper.from(movieDetails)

        // When & Assertions
        val flow = repository.getMovieDetails(TestDataGenerator.movieId1)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData.id).isEqualTo(movieDetails.id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMovieDetails(TestDataGenerator.movieId1) }
        coVerify { localDataSourceContract.insertMovieDetails(movieDetailsDataMapper.from(movieDetails)) }
    }

    @Test
    fun test_get_movie_details_from_local_when_remote_fail() = runBlockingTest {
        val movieDetails = TestDataGenerator.generateMovieDetailsItem(TestDataGenerator.movieId1)

        // Given
        coEvery { remoteDataSourceContract.getMovieDetails(TestDataGenerator.movieId1) } throws Exception()
        coEvery { localDataSourceContract.getMovieDetailsByIdFromDataBase(TestDataGenerator.movieId1) } returns movieDetailsDataMapper.from(movieDetails)

        // When && Assertions
        val flow = repository.getMovieDetails(TestDataGenerator.movieId1)
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData.id).isEqualTo(movieDetails.id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMovieDetails(TestDataGenerator.movieId1) }
        coVerify { localDataSourceContract.getMovieDetailsByIdFromDataBase(TestDataGenerator.movieId1) }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_movies() = runBlockingTest {
        // Given
        coEvery { remoteDataSourceContract.getMovieDetails(TestDataGenerator.movieId1) } throws Exception()
        coEvery { localDataSourceContract.getMovieDetailsByIdFromDataBase(TestDataGenerator.movieId1) } throws Exception()

        // When && Assertions
        val flow = repository.getMovieDetails(TestDataGenerator.movieId1)
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getMovieDetails(TestDataGenerator.movieId1) }
        coVerify { localDataSourceContract.getMovieDetailsByIdFromDataBase(TestDataGenerator.movieId1) }
    }
}