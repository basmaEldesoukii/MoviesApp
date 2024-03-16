package com.banquemisr.movieslist.data.local

interface MoviesListLocalDataSourceContract {
    suspend fun getMoviesListFromDataBase(): List<MovieLocalEntity>
    suspend fun insertMoviesList(data: List<MovieLocalEntity>): List<Long>
    suspend fun clearMoviesListCashed(): Int
}