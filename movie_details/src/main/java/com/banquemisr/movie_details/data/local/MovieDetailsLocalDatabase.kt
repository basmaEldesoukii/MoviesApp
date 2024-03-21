package com.banquemisr.movie_details.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieDetailsLocalEntity::class], version = 2, exportSchema = false)
abstract class MovieDetailsLocalDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao
}