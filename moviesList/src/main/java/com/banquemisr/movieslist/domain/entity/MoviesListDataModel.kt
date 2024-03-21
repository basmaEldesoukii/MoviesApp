package com.banquemisr.movieslist.domain.entity

import com.google.gson.annotations.SerializedName

data class MoviesListDataModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)