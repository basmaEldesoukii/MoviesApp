package com.banquemisr.challenge05.di_modules

import android.content.Context
import androidx.room.Room
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
            .build()
    }
    @Provides
    @Singleton
    fun provideMoviesListDao(moviesListDataBase: MoviesListLocalDatabase) =
        moviesListDataBase.moviesListDao()
}