package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.BuildConfig
import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface MoviesListRemoteServices {

    @Headers(
        "Authorization: " + BuildConfig.API_KEY,
        "accept: application/json",
    )
    @GET("now_playing")
    suspend fun getNowPlayingList(): MoviesListDataModel


    @Headers(
        "Authorization: " + BuildConfig.API_KEY,
        "accept: application/json",
    )
    @GET("popular")
    suspend fun getPopularList(): MoviesListDataModel

    @Headers(
        "Authorization: " + BuildConfig.API_KEY,
        "accept: application/json",
    )
    @GET("upcoming")
    suspend fun getUpcomingList(): MoviesListDataModel

}