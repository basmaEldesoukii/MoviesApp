package com.banquemisr.movieslist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieLocalEntity::class], version = 2, exportSchema = false)
abstract class MoviesListLocalDatabase: RoomDatabase() {
    abstract fun moviesListDao(): MoviesListDao
}