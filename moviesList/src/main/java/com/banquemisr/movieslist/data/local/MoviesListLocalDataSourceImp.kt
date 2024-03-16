package com.banquemisr.movieslist.data.local

import javax.inject.Inject

class MoviesListLocalDataSourceImp @Inject constructor(
    private val moviesListDao: MoviesListDao,
) : MoviesListLocalDataSourceContract {

    override suspend fun getMoviesListFromDataBase(): List<MovieLocalEntity> {
        return moviesListDao.getMoviesList()
    }

    override suspend fun insertMoviesList(data: List<MovieLocalEntity>): List<Long> {
        return moviesListDao.addMovie(data)
    }

    override suspend fun clearMoviesListCashed(): Int {
        return moviesListDao.clearMoviesListCash()
    }
}