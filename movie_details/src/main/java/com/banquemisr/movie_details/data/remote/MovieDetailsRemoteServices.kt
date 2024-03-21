package com.banquemisr.movie_details.data.remote

import com.banquemisr.movie_details.BuildConfig
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieDetailsRemoteServices {

    @Headers(
        "Authorization: " + BuildConfig.API_KEY,
        "accept: application/json",
    )
    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, ): MovieDetailsDataModel
}