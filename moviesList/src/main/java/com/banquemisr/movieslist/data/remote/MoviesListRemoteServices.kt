package com.banquemisr.movieslist.data.remote

import com.banquemisr.movieslist.domain.entity.NowPlayingListDataModel
import com.banquemisr.movieslist.domain.entity.PopularListDataModel
import com.banquemisr.movieslist.domain.entity.UpcomingListDataModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface MoviesListRemoteServices {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTYwNjk2MGRhZWU0YWU3MzcwMjdmMjg0YjgxZDI4YSIsInN1YiI6IjY1ZjU2NjQxZTAzOWYxMDE3ZDAzZDZiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NLBFRopdn6btZJBLi1z5a7MPCnhjWlEotx3a_Ukj9NY",
        "accept: application/json",
    )
    @GET("/now_playing")
    suspend fun getNowPlayingList(): List<NowPlayingListDataModel>

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTYwNjk2MGRhZWU0YWU3MzcwMjdmMjg0YjgxZDI4YSIsInN1YiI6IjY1ZjU2NjQxZTAzOWYxMDE3ZDAzZDZiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NLBFRopdn6btZJBLi1z5a7MPCnhjWlEotx3a_Ukj9NY",
        "accept: application/json",
    )
    @GET("/popular")
    suspend fun getPopularList(): List<PopularListDataModel>

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTYwNjk2MGRhZWU0YWU3MzcwMjdmMjg0YjgxZDI4YSIsInN1YiI6IjY1ZjU2NjQxZTAzOWYxMDE3ZDAzZDZiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NLBFRopdn6btZJBLi1z5a7MPCnhjWlEotx3a_Ukj9NY",
        "accept: application/json",
    )
    @GET("/upcoming")
    suspend fun getUpcomingList(): List<UpcomingListDataModel>

}