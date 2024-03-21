package com.banquemisr.movie_details.data.local

interface MovieDetailsLocalDataSourceContract {

    suspend fun getMovieDetailsByIdFromDataBase(movieId: Int): MovieDetailsLocalEntity

    suspend fun insertMovieDetails(item: MovieDetailsLocalEntity): Long

    suspend fun clearMovieDetailsCashed(): Int
}