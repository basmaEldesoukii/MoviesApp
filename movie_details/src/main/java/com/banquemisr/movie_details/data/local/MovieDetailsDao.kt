package com.banquemisr.movie_details.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDetailsDao {
    @Query("select * from MovieDetails where id=:id")
    suspend fun getMovieByID(id: Int): MovieDetailsLocalEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieDetails(movie: MovieDetailsLocalEntity): Long

    @Query("delete from MovieDetails")
    suspend fun clearMovieDetailsCash():Int
}