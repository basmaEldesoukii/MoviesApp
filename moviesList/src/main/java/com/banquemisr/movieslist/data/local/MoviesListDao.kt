package com.banquemisr.movieslist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesListDao {
    @Query("select * from MoviesList")
    suspend fun getMoviesList(): List<MovieLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: List<MovieLocalEntity>): List<Long>

    @Query("delete from MoviesList")
    suspend fun clearMoviesListCash():Int
}