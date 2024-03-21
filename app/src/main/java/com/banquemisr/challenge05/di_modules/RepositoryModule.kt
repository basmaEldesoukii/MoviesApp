package com.banquemisr.challenge05.di_modules

import com.banquemisr.common.Mapper
import com.banquemisr.movie_details.data.local.MovieDetailsDao
import com.banquemisr.movie_details.data.local.MovieDetailsLocalDataSourceImp
import com.banquemisr.movie_details.data.local.MovieDetailsLocalEntity
import com.banquemisr.movie_details.data.remote.MovieDetailsRemoteDataSourceImp
import com.banquemisr.movie_details.data.remote.MovieDetailsRemoteServices
import com.banquemisr.movie_details.data.repo.MovieDetailsRepositoryImp
import com.banquemisr.movie_details.domain.contract.MovieDetailsRepositoryContract
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import com.banquemisr.movieslist.data.repo.MoviesListRepositoryImp
import com.banquemisr.movieslist.data.local.MovieLocalEntity
import com.banquemisr.movieslist.data.local.MoviesListDao
import com.banquemisr.movieslist.data.local.MoviesListLocalDataSourceImp
import com.banquemisr.movieslist.data.remote.MoviesListRemoteDataSourceImp
import com.banquemisr.movieslist.data.remote.MoviesListRemoteServices
import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract
import com.banquemisr.movieslist.domain.entity.Movie
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
        moviesListDao: MoviesListDao,
    ) = MoviesListLocalDataSourceImp(moviesListDao)

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
        mapper: Mapper<Movie, MovieLocalEntity>
    ): MoviesListRepositoryContract =
        MoviesListRepositoryImp(
            localDataSource,
            remoteDataSource,
            mapper
        )

    @Singleton
    @Provides
    fun provideMovieDetailsLocalDataSource(
        movieDetailsDao: MovieDetailsDao,
    ) = MovieDetailsLocalDataSourceImp(movieDetailsDao)

    @Singleton
    @Provides
    fun provideMovieDetailsRemoteDataSource(
        apiService: MovieDetailsRemoteServices,
    ) = MovieDetailsRemoteDataSourceImp(
        apiService
    )

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(
        localDataSource: MovieDetailsLocalDataSourceImp,
        remoteDataSource: MovieDetailsRemoteDataSourceImp,
        mapper: Mapper<MovieDetailsDataModel, MovieDetailsLocalEntity>
    ): MovieDetailsRepositoryContract =
        MovieDetailsRepositoryImp(
            localDataSource,
            remoteDataSource,
            mapper
        )
}