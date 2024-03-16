package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.domain.entity.MoviesListDataModel

interface MoviesListRemoteDataSourceContract {
    suspend fun getNowPlayingList(): List<MoviesListDataModel>
    suspend fun getPopularList(): List<MoviesListDataModel>
    suspend fun getUpcomingList(): List<MoviesListDataModel>
}