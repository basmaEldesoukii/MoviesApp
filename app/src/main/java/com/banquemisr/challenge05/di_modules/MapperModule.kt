package com.banquemisr.challenge05.di_modules

import com.banquemisr.common.Mapper
import com.banquemisr.movie_details.data.local.MovieDetailsLocalEntity
import com.banquemisr.movie_details.data.repo.MovieDetailsDataMapper
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import com.banquemisr.movieslist.data.local.MovieLocalEntity
import com.banquemisr.movieslist.data.repo.MoviesListDataMapper
import com.banquemisr.movieslist.domain.entity.Movie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Binds
    abstract fun bindsLocalMoviesListDataMapperMapper(mapper: MoviesListDataMapper): Mapper<Movie, MovieLocalEntity>

    @Binds
    abstract fun bindsLocalMovieDetailsItemMapper(mapper: MovieDetailsDataMapper): Mapper<MovieDetailsDataModel, MovieDetailsLocalEntity>

}