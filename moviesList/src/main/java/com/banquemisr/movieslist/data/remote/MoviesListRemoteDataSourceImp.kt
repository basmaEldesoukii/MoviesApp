package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import javax.inject.Inject

class MoviesListRemoteDataSourceImp @Inject constructor(
    private val apiService: MoviesListRemoteServices
) : MoviesListRemoteDataSourceContract {
    override suspend fun getNowPlayingList(): List<MoviesListDataModel> {
        return apiService.getNowPlayingList()
    }
    override suspend fun getPopularList(): List<MoviesListDataModel> {
        return apiService.getPopularList()
    }
    override suspend fun getUpcomingList(): List<MoviesListDataModel> {
        return apiService.getUpcomingList()
    }
}