package com.banquemisr.challenge05.di_modules

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
    fun GetNowPlayingListUseCase(
        moviesListRepositoryContract: MoviesListRepositoryContract
    ): GetNowPlayingListUseCase = GetNowPlayingListUseCase(moviesListRepositoryContract)

    @Singleton
    @Provides
    fun GetPopularListUseCase(
        moviesListRepositoryContract: MoviesListRepositoryContract
    ): GetPopularListUseCase = GetPopularListUseCase(moviesListRepositoryContract)

    @Singleton
    @Provides
    fun GetUpcomingListUseCase(
        moviesListRepositoryContract: MoviesListRepositoryContract
    ): GetUpcomingListUseCase = GetUpcomingListUseCase(moviesListRepositoryContract)
}