package com.banquemisr.movieslist.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.banquemisr.common.Resource
import com.banquemisr.movieslist.data.local.MoviesListLocalDataSourceContract
import com.banquemisr.movieslist.data.remote.MoviesListRemoteDataSourceContract
import com.banquemisr.movieslist.data.repo.MoviesListDataMapper
import com.banquemisr.movieslist.data.repo.MoviesListRepositoryImp
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
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
@SmallTest
class MoviesListRepositoryImpUnitTest {

    @MockK
    private lateinit var localDataSourceContract: MoviesListLocalDataSourceContract

    @MockK
    private lateinit var remoteDataSourceContract: MoviesListRemoteDataSourceContract

    private val moviesListDataMapper = MoviesListDataMapper()

    private lateinit var repository: MoviesListRepositoryImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = MoviesListRepositoryImp(
            localDataSource = localDataSourceContract,
            remoteDataSource = remoteDataSourceContract,
            moviesListDataMapper = moviesListDataMapper
        )
    }

    @Test
    fun test_get_now_playing_list_from_remote_success() = runBlockingTest{
        val movies = TestDataGenerator.generateMoviesListResponse()
        val affectedIds = MutableList(movies.results.size) { index -> index.toLong() }

        // Given
        coEvery { remoteDataSourceContract.getNowPlayingList() } returns movies
        coEvery { localDataSourceContract.insertMoviesList(moviesListDataMapper.fromList(movies.results)) } returns affectedIds
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } returns moviesListDataMapper.fromList(movies.results)

        // When & Assertions
        val flow = repository.getNowPlayingList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].id).isEqualTo(movies.results[0].id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getNowPlayingList() }
        coVerify { localDataSourceContract.insertMoviesList(moviesListDataMapper.fromList(movies.results)) }
    }

    @Test
    fun test_get_now_playing_list_from_local_when_remote_fail() = runBlockingTest {
        val movies = TestDataGenerator.generateMoviesList()

        // Given
        coEvery { remoteDataSourceContract.getNowPlayingList() } throws Exception()
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } returns moviesListDataMapper.fromList(movies)

        // When && Assertions
        val flow = repository.getNowPlayingList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].id).isEqualTo(movies[0].id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getNowPlayingList() }
        coVerify { localDataSourceContract.getMoviesListFromDataBase() }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_now_playing_list() = runBlockingTest {
        // Given
        coEvery { remoteDataSourceContract.getNowPlayingList() } throws Exception()
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } throws Exception()

        // When && Assertions
        val flow = repository.getNowPlayingList()
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getNowPlayingList() }
        coVerify { localDataSourceContract.getMoviesListFromDataBase() }
    }


    @Test
    fun test_get_popular_list_from_remote_success() = runBlockingTest{
        val movies = TestDataGenerator.generateMoviesListResponse()
        val affectedIds = MutableList(movies.results.size) { index -> index.toLong() }

        // Given
        coEvery { remoteDataSourceContract.getPopularList() } returns movies
        coEvery { localDataSourceContract.insertMoviesList(moviesListDataMapper.fromList(movies.results)) } returns affectedIds
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } returns moviesListDataMapper.fromList(movies.results)

        // When & Assertions
        val flow = repository.getPopularList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].id).isEqualTo(movies.results[0].id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getPopularList() }
        coVerify { localDataSourceContract.insertMoviesList(moviesListDataMapper.fromList(movies.results)) }
    }

    @Test
    fun test_get_popular_list_from_local_when_remote_fail() = runBlockingTest {
        val movies = TestDataGenerator.generateMoviesList()

        // Given
        coEvery { remoteDataSourceContract.getPopularList() } throws Exception()
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } returns moviesListDataMapper.fromList(movies)

        // When && Assertions
        val flow = repository.getPopularList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].id).isEqualTo(movies[0].id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getPopularList() }
        coVerify { localDataSourceContract.getMoviesListFromDataBase() }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_popular_list() = runBlockingTest {
        // Given
        coEvery { remoteDataSourceContract.getPopularList() } throws Exception()
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } throws Exception()

        // When && Assertions
        val flow = repository.getPopularList()
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getPopularList() }
        coVerify { localDataSourceContract.getMoviesListFromDataBase() }
    }


    @Test
    fun test_get_upcoming_list_from_remote_success() = runBlockingTest{
        val movies = TestDataGenerator.generateMoviesListResponse()
        val affectedIds = MutableList(movies.results.size) { index -> index.toLong() }

        // Given
        coEvery { remoteDataSourceContract.getUpcomingList() } returns movies
        coEvery { localDataSourceContract.insertMoviesList(moviesListDataMapper.fromList(movies.results)) } returns affectedIds
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } returns moviesListDataMapper.fromList(movies.results)

        // When & Assertions
        val flow = repository.getUpcomingList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].id).isEqualTo(movies.results[0].id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getUpcomingList() }
        coVerify { localDataSourceContract.insertMoviesList(moviesListDataMapper.fromList(movies.results)) }
    }

    @Test
    fun test_get_upcoming_list_from_local_when_remote_fail() = runBlockingTest {
        val movies = TestDataGenerator.generateMoviesList()

        // Given
        coEvery { remoteDataSourceContract.getUpcomingList() } throws Exception()
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } returns moviesListDataMapper.fromList(movies)

        // When && Assertions
        val flow = repository.getUpcomingList()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0].id).isEqualTo(movies[0].id)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getUpcomingList() }
        coVerify { localDataSourceContract.getMoviesListFromDataBase() }
    }

    @Test
    fun test_for_error_state_when_remote_and_local_fail_of_getting_upcoming_list() = runBlockingTest {
        // Given
        coEvery { remoteDataSourceContract.getUpcomingList() } throws Exception()
        coEvery { localDataSourceContract.getMoviesListFromDataBase() } throws Exception()

        // When && Assertions
        val flow = repository.getUpcomingList()
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSourceContract.getUpcomingList() }
        coVerify { localDataSourceContract.getMoviesListFromDataBase() }
    }


}