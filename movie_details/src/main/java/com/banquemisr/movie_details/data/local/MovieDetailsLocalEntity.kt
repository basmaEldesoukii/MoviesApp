package com.banquemisr.movie_details.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieDetails")
data class MovieDetailsLocalEntity (
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "original_title")
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int,
    @ColumnInfo(name = "budget")
    val budget: Int,
    @ColumnInfo(name = "homepage")
    val homepage: String,
    @ColumnInfo(name = "imdb_id")
    val imdb_id: String,
    @ColumnInfo(name = "revenue")
    val revenue: Int,
    @ColumnInfo(name = "runtime")
    val runtime: Int,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "tagline")
    val tagline: String
)