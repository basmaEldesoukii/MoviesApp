package com.banquemisr.challenge05.di_modules

import com.banquemisr.common.Mapper
import com.banquemisr.movieslist.data.repo.MoviesListRepositoryImp
import com.banquemisr.movieslist.data.local.MovieLocalEntity
import com.banquemisr.movieslist.data.local.MoviesListDao
import com.banquemisr.movieslist.data.local.MoviesListLocalDataSourceImp
import com.banquemisr.movieslist.data.remote.MoviesListRemoteDataSourceImp
import com.banquemisr.movieslist.data.remote.MoviesListRemoteServices
import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract
import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMoviesListLocalDataSource(
        MoviesListDao: MoviesListDao,
    ) = MoviesListLocalDataSourceImp(MoviesListDao)

    @Singleton
    @Provides
    fun provideMoviesListRemoteDataSource(
        apiService: MoviesListRemoteServices,
    ) = MoviesListRemoteDataSourceImp(
        apiService
    )

    @Singleton
    @Provides
    fun provideMoviesListRepository(
        localDataSource: MoviesListLocalDataSourceImp,
        remoteDataSource: MoviesListRemoteDataSourceImp,
        mapper: Mapper<MoviesListDataModel, MovieLocalEntity>
    ): MoviesListRepositoryContract =
        MoviesListRepositoryImp(
            localDataSource,
            remoteDataSource,
            mapper
        )
}