package com.banquemisr.movieslist.domain.contract

import com.banquemisr.common.Resource
import com.banquemisr.movieslist.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesListRepositoryContract {

    suspend fun getNowPlayingList(): Flow<Resource<List<Movie>>>
    suspend fun getPopularList(): Flow<Resource<List<Movie>>>
    suspend fun getUpcomingList(): Flow<Resource<List<Movie>>>

}