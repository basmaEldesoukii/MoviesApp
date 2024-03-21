package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.domain.entity.MoviesListDataModel

interface MoviesListRemoteDataSourceContract {
    suspend fun getNowPlayingList(): MoviesListDataModel
    suspend fun getPopularList(): MoviesListDataModel
    suspend fun getUpcomingList(): MoviesListDataModel
}