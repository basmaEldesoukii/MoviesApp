package com.banquemisr.movie_details.data.remote

import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImp @Inject constructor(
    private val apiService: MovieDetailsRemoteServices
) : MovieDetailsRemoteDataSourceContract {
    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDataModel {
        return apiService.getMovieDetails(movieId)
    }
}