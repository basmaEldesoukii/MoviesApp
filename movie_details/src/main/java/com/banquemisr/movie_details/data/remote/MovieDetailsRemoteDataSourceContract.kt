package com.banquemisr.movie_details.data.remote

import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel

interface MovieDetailsRemoteDataSourceContract {
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDataModel
}