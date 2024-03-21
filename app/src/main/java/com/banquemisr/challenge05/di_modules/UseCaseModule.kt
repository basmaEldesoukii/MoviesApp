package com.banquemisr.challenge05.di_modules

import com.banquemisr.movie_details.domain.contract.MovieDetailsRepositoryContract
import com.banquemisr.movie_details.domain.usecase.GetMovieDetailsUseCase
import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract
import com.banquemisr.movieslist.domain.usecase.GetNowPlayingListUseCase
import com.banquemisr.movieslist.domain.usecase.GetPopularListUseCase
import com.banquemisr.movieslist.domain.usecase.GetUpcomingListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNowPlayingListUseCase(
        repository: MoviesListRepositoryContract
    ): GetNowPlayingListUseCase {
        return GetNowPlayingListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetPopularListUseCase(
        repository: MoviesListRepositoryContract
    ): GetPopularListUseCase {
        return GetPopularListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetUpcomingListUseCase(
        repository: MoviesListRepositoryContract
    ): GetUpcomingListUseCase {
        return GetUpcomingListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(
        repository: MovieDetailsRepositoryContract,
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}