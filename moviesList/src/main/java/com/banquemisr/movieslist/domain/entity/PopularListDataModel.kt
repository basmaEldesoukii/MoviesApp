package com.banquemisr.movieslist.domain.entity

data class PopularListDataModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)