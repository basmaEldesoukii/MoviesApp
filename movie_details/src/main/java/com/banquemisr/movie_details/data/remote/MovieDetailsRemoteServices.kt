package com.banquemisr.movie_details.data.remote

import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieDetailsRemoteServices {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTYwNjk2MGRhZWU0YWU3MzcwMjdmMjg0YjgxZDI4YSIsInN1YiI6IjY1ZjU2NjQxZTAzOWYxMDE3ZDAzZDZiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NLBFRopdn6btZJBLi1z5a7MPCnhjWlEotx3a_Ukj9NY",
        "accept: application/json",
    )
    @GET("{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int, ): MovieDetailsDataModel
}