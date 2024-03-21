package com.banquemisr.movieslist.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract
import com.banquemisr.movieslist.domain.usecase.GetNowPlayingListUseCase
import com.banquemisr.movieslist.domain.usecase.GetPopularListUseCase
import com.banquemisr.movieslist.domain.usecase.GetUpcomingListUseCase
import com.banquemisr.movieslist.utils.TestDataGenerator
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
class MoviesListUseCaseUnitTest {

    @MockK
    private lateinit var repository: MoviesListRepositoryContract

    private lateinit var getNowPlayingListUseCase: GetNowPlayingListUseCase
    private lateinit var getPopularListUseCase: GetPopularListUseCase
    private lateinit var getUpcomingListUseCase: GetUpcomingListUseCase


    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getNowPlayingListUseCase = GetNowPlayingListUseCase(
            moviesListRepositoryContract = repository,
        )
        getPopularListUseCase = GetPopularListUseCase(
            moviesListRepositoryContract = repository,
        )
        getUpcomingListUseCase = GetUpcomingListUseCase(
            moviesListRepositoryContract = repository,
        )
    }

    @Test
    fun test_get_now_playing_list_success() = runBlockingTest{
        val movies = TestDataGenerator.generateMoviesList()
        val moviesFlow = flowOf(Resource.Success(movies))

        // Given
        coEvery { repository.getNowPlayingList() } returns moviesFlow

        // When & Assertions
        val result = getNowPlayingListUseCase.invoke()
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(moviesFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getNowPlayingList() }
    }

    @Test
    fun test_get_now_playing_list_fail() = runBlockingTest{

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getNowPlayingList() } returns errorFlow

        // When & Assertions
        val result = getNowPlayingListUseCase.invoke()
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getNowPlayingList() }
    }

    @Test
    fun test_get_popular_list_success() = runBlockingTest{
        val movies = TestDataGenerator.generateMoviesList()
        val moviesFlow = flowOf(Resource.Success(movies))

        // Given
        coEvery { repository.getPopularList() } returns moviesFlow

        // When & Assertions
        val result = getPopularListUseCase.invoke()
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(moviesFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getPopularList() }
    }

    @Test
    fun test_get_popular_list_fail() = runBlockingTest{

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getPopularList() } returns errorFlow

        // When & Assertions
        val result = getPopularListUseCase.invoke()
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getPopularList() }
    }


    @Test
    fun test_get_upcoming_list_success() = runBlockingTest{
        val movies = TestDataGenerator.generateMoviesList()
        val moviesFlow = flowOf(Resource.Success(movies))

        // Given
        coEvery { repository.getUpcomingList() } returns moviesFlow

        // When & Assertions
        val result = getUpcomingListUseCase.invoke()
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(moviesFlow.first().data)
            expectComplete()
        }

        // Then
        coVerify { repository.getUpcomingList() }
    }

    @Test
    fun test_get_upcoming_list_fail() = runBlockingTest{

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getUpcomingList() } returns errorFlow

        // When & Assertions
        val result = getUpcomingListUseCase.invoke()
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getUpcomingList() }
    }
}