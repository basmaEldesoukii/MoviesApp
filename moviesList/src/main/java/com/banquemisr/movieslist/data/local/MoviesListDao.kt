package com.banquemisr.movieslist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesListDao {
    @Query("select * from MoviesList")
    fun getMoviesList(): List<MovieLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: List<MovieLocalEntity>): List<Long>

    @Query("delete from MoviesList")
    fun clearMoviesListCash():Int
}