package com.banquemisr.movie_details.domain.contract

import com.banquemisr.common.Resource
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepositoryContract {
    suspend fun getMovieDetails(id: Int): Flow<Resource<MovieDetailsDataModel>>
}