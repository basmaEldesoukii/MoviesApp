package com.banquemisr.movieslist.data

import androidx.test.filters.SmallTest
import com.banquemisr.movieslist.data.remote.MoviesListRemoteDataSourceImp
import com.banquemisr.movieslist.data.remote.MoviesListRemoteServices
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
class MoviesListRemoteDataSourceImpUnitTest {

    @MockK
    private lateinit var remoteServices: MoviesListRemoteServices

    private lateinit var remoteDataSource: MoviesListRemoteDataSourceImp

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = MoviesListRemoteDataSourceImp(
            apiService = remoteServices
        )
    }

    @Test
    fun test_get_now_playing_list_success() = runBlockingTest {
        val moviesResponse = TestDataGenerator.generateMoviesListResponse()

        // Given
        coEvery { remoteServices.getNowPlayingList() } returns moviesResponse

        // When
        val result = remoteDataSource.getNowPlayingList()

        // Then
        coVerify { remoteServices.getNowPlayingList() }

        // Assertion
        val expected = moviesResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_now_playing_list_fail() = runBlockingTest {
        // Given
        coEvery { remoteServices.getNowPlayingList() } throws Exception()

        // When
        remoteDataSource.getNowPlayingList()

        // Then
        coVerify { remoteServices.getNowPlayingList() }
    }

    @Test
    fun test_get_popular_list_success() = runBlockingTest {
        val moviesResponse = TestDataGenerator.generateMoviesListResponse()

        // Given
        coEvery { remoteServices.getPopularList() } returns moviesResponse

        // When
        val result = remoteDataSource.getPopularList()

        // Then
        coVerify { remoteServices.getPopularList() }

        // Assertion
        val expected = moviesResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_popular_list_fail() = runBlockingTest {
        // Given
        coEvery { remoteServices.getPopularList() } throws Exception()

        // When
        remoteDataSource.getPopularList()

        // Then
        coVerify { remoteServices.getPopularList() }
    }

    @Test
    fun test_get_upcoming_list_success() = runBlockingTest {
        val moviesResponse = TestDataGenerator.generateMoviesListResponse()

        // Given
        coEvery { remoteServices.getUpcomingList() } returns moviesResponse

        // When
        val result = remoteDataSource.getUpcomingList()

        // Then
        coVerify { remoteServices.getUpcomingList() }

        // Assertion
        val expected = moviesResponse
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_upcoming_list_fail() = runBlockingTest {
        // Given
        coEvery { remoteServices.getUpcomingList() } throws Exception()

        // When
        remoteDataSource.getUpcomingList()

        // Then
        coVerify { remoteServices.getUpcomingList() }
    }

}