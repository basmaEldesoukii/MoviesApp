package com.banquemisr.movie_details.data

import androidx.test.filters.SmallTest
import com.banquemisr.movie_details.data.local.MovieDetailsDao
import com.banquemisr.movie_details.data.local.MovieDetailsLocalDataSourceImp
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
class MovieDetailsLocalDataSourceImpUnitTest {
    @MockK
    private lateinit var movieDetailsDao: MovieDetailsDao

    private lateinit var localDataSourceImp: MovieDetailsLocalDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)
        localDataSourceImp = MovieDetailsLocalDataSourceImp(
            movieDetailsDao = movieDetailsDao
        )
    }

    @Test
    fun test_add_movie_details_success() = runBlockingTest {
        val localMovies = TestDataGenerator.generateMovieDetailsLocalData()
        val expected = 1L

        // Given
        coEvery { movieDetailsDao.addMovieDetails(any()) } returns expected

        // When
        val returned = localDataSourceImp.insertMovieDetails(localMovies)

        // Then
        coVerify { movieDetailsDao.addMovieDetails(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_add_movie_details_fail() = runBlockingTest {
        val localMovies = TestDataGenerator.generateMovieDetailsLocalData()

        // Given
        coEvery { movieDetailsDao.addMovieDetails(any()) } throws Exception()

        // When
        localDataSourceImp.insertMovieDetails(localMovies)

        // Then
        coVerify { movieDetailsDao.addMovieDetails(any()) }
    }

    @Test
    fun test_get_movie_details_success() = runBlockingTest {
        val movieDetails = TestDataGenerator.generateMovieDetailsLocalData()
        val expected = movieDetails

        // Given
        coEvery { movieDetailsDao.getMovieByID(any()) } returns expected

        // When
        val returned = localDataSourceImp.getMovieDetailsByIdFromDataBase(movieDetails.id)

        // Then
        coVerify { movieDetailsDao.getMovieByID(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_movie_details_fail() = runBlockingTest {
        val movieDetails = TestDataGenerator.generateMovieDetailsLocalData()

        // Given
        coEvery { movieDetailsDao.getMovieByID(any()) } throws Exception()

        // When
        localDataSourceImp.getMovieDetailsByIdFromDataBase(movieDetails.id)

        // Then
        coVerify { movieDetailsDao.getMovieByID(any()) }
    }

    @Test
    fun test_clear_all_movie_details_success() = runBlockingTest {
        val localMovies = TestDataGenerator.generateListOfMovieDetailsItem()
        val expected = localMovies.size // Affected row count

        // Given
        coEvery { movieDetailsDao.clearMovieDetailsCash() } returns expected

        // When
        val returned = localDataSourceImp.clearMovieDetailsCashed()

        // Then
        coVerify { movieDetailsDao.clearMovieDetailsCash() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_clear_all_movie_details_fail() = runBlockingTest {
        // Given
        coEvery { movieDetailsDao.clearMovieDetailsCash() } throws Exception()

        // When
        localDataSourceImp.clearMovieDetailsCashed()

        // Then
        coVerify { movieDetailsDao.clearMovieDetailsCash() }
    }
}