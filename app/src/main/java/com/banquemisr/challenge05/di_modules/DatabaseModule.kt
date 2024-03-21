package com.banquemisr.challenge05.di_modules

import android.content.Context
import androidx.room.Room
import com.banquemisr.movie_details.data.local.MovieDetailsLocalDatabase
import com.banquemisr.movieslist.data.local.MoviesListLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMoviesListDatabase(@ApplicationContext application: Context): MoviesListLocalDatabase {
        return Room
            .databaseBuilder(application, MoviesListLocalDatabase::class.java, "Movies_list_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideMoviesListDao(moviesListDataBase: MoviesListLocalDatabase) =
        moviesListDataBase.moviesListDao()

    @Provides
    @Singleton
    fun provideMovieDetailsDatabase(@ApplicationContext application: Context): MovieDetailsLocalDatabase {
        return Room
            .databaseBuilder(application, MovieDetailsLocalDatabase::class.java, "movie_details_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDetailsDao(movieDetailsDataBase: MovieDetailsLocalDatabase) =
        movieDetailsDataBase.movieDetailsDao()
}