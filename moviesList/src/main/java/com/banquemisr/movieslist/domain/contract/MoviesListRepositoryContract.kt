package com.banquemisr.movieslist.domain.contract

import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.entity.NowPlayingListDataModel
import com.banquemisr.movieslist.domain.entity.PopularListDataModel
import com.banquemisr.movieslist.domain.entity.UpcomingListDataModel
import kotlinx.coroutines.flow.Flow

interface MoviesListRepositoryContract {

    suspend fun getNowPlayingList(): Flow<Resource<List<NowPlayingListDataModel>>>
    suspend fun getPopularList(): Flow<Resource<List<PopularListDataModel>>>
    suspend fun getUpcomingList(): Flow<Resource<List<UpcomingListDataModel>>>

}