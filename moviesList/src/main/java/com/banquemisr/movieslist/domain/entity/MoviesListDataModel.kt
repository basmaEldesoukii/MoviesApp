package com.banquemisr.movieslist.domain.entity

data class MoviesListDataModel(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)