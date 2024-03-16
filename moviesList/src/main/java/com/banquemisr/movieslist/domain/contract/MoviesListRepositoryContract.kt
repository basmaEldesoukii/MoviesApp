package com.banquemisr.movieslist.domain.contract

import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import kotlinx.coroutines.flow.Flow

interface MoviesListRepositoryContract {

    suspend fun getNowPlayingList(): Flow<Resource<List<MoviesListDataModel>>>
    suspend fun getPopularList(): Flow<Resource<List<MoviesListDataModel>>>
    suspend fun getUpcomingList(): Flow<Resource<List<MoviesListDataModel>>>

}