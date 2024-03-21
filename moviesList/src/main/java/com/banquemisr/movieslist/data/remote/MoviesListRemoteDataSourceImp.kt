package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import javax.inject.Inject

class MoviesListRemoteDataSourceImp @Inject constructor(
    private val apiService: MoviesListRemoteServices
) : MoviesListRemoteDataSourceContract {
    override suspend fun getNowPlayingList(): MoviesListDataModel {
        return apiService.getNowPlayingList()
    }
    override suspend fun getPopularList(): MoviesListDataModel {
        return apiService.getPopularList()
    }
    override suspend fun getUpcomingList(): MoviesListDataModel {
        return apiService.getUpcomingList()
    }
}