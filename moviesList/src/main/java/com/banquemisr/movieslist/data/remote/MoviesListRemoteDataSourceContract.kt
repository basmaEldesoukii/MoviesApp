package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.domain.entity.NowPlayingListDataModel
import com.banquemisr.movieslist.domain.entity.PopularListDataModel
import com.banquemisr.movieslist.domain.entity.UpcomingListDataModel

interface MoviesListRemoteDataSourceContract {
    suspend fun getNowPlayingList(): List<NowPlayingListDataModel>
    suspend fun getPopularList(): List<PopularListDataModel>
    suspend fun getUpcomingList(): List<UpcomingListDataModel>
}