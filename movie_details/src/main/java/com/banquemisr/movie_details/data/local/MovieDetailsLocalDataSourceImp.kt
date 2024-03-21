package com.banquemisr.movie_details.data.local

import javax.inject.Inject

class MovieDetailsLocalDataSourceImp @Inject constructor(
    private val movieDetailsDao: MovieDetailsDao,
) : MovieDetailsLocalDataSourceContract {

    override suspend fun getMovieDetailsByIdFromDataBase(movieId: Int): MovieDetailsLocalEntity {
        return movieDetailsDao.getMovieByID(movieId)
    }

    override suspend fun insertMovieDetails(item: MovieDetailsLocalEntity): Long {
        return movieDetailsDao.addMovieDetails(item)
    }

    override suspend fun clearMovieDetailsCashed(): Int {
        return movieDetailsDao.clearMovieDetailsCash()
    }
}