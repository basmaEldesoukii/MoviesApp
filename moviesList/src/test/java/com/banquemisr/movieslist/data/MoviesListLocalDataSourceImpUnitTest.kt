package com.banquemisr.movieslist.data

import androidx.test.filters.SmallTest
import com.banquemisr.movieslist.data.local.MoviesListDao
import com.banquemisr.movieslist.data.local.MoviesListLocalDataSourceImp
import com.banquemisr.movieslist.utils.TestDataGenerator
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
class MoviesListLocalDataSourceImpUnitTest {

    @MockK
    private lateinit var moviesListDao: MoviesListDao

    private lateinit var localDataSourceImp: MoviesListLocalDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)
        localDataSourceImp = MoviesListLocalDataSourceImp(
            moviesListDao = moviesListDao
        )
    }

    @Test
    fun test_add_movies_success() = runBlockingTest {
        val localMovies = TestDataGenerator.generateListOfLocalMovieItem()
        val expected = MutableList(localMovies.size) { index -> index.toLong() }

        // Given
        coEvery { moviesListDao.addMovie(any()) } returns expected

        // When
        val returned = localDataSourceImp.insertMoviesList(localMovies)

        // Then
        coVerify { moviesListDao.addMovie(any()) }

        // Assertion
        Truth.assertThat(returned).hasSize(expected.size)
    }

    @Test(expected = Exception::class)
    fun test_add_movies_fail() = runBlockingTest {
        val localMovies = TestDataGenerator.generateListOfLocalMovieItem()

        // Given
        coEvery { moviesListDao.addMovie(any()) } throws Exception()

        // When
        localDataSourceImp.insertMoviesList(localMovies)

        // Then
        coVerify { moviesListDao.addMovie(any()) }
    }

    @Test
    fun test_get_movies_success() = runBlockingTest {
        val expected = TestDataGenerator.generateListOfLocalMovieItem()

        // Given
        coEvery { moviesListDao.getMoviesList() } returns expected

        // When
        val returned = localDataSourceImp.getMoviesListFromDataBase()

        // Then
        coVerify { moviesListDao.getMoviesList() }

        // Assertion
        Truth.assertThat(returned).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_movies_fail() = runBlockingTest {
        // Given
        coEvery { moviesListDao.getMoviesList() } throws Exception()

        // When
        localDataSourceImp.getMoviesListFromDataBase()

        // Then
        coVerify { moviesListDao.getMoviesList() }
    }

    @Test
    fun test_clear_all_movies_success() = runBlockingTest {
        val localMovies = TestDataGenerator.generateListOfLocalMovieItem()
        val expected = localMovies.size // Affected row count

        // Given
        coEvery { moviesListDao.clearMoviesListCash() } returns expected

        // When
        val returned = localDataSourceImp.clearMoviesListCashed()

        // Then
        coVerify { moviesListDao.clearMoviesListCash() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_clear_all_movies_fail() = runBlockingTest {
        // Given
        coEvery { moviesListDao.clearMoviesListCash() } throws Exception()

        // When
        localDataSourceImp.clearMoviesListCashed()

        // Then
        coVerify { moviesListDao.clearMoviesListCash() }
    }
}