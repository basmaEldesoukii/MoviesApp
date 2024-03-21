package com.banquemisr.movie_details.utils

import com.banquemisr.movie_details.data.local.MovieDetailsLocalEntity
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel

class TestDataGenerator {
    companion object {
        const val movieId1 = 1
        private const val movieId2 = 2
        private const val movieId3 = 3

        fun generateListOfMovieDetailsItem(): List<MovieDetailsDataModel> {
            return listOf(
                generateMovieDetailsItem(movieId1),
                generateMovieDetailsItem(movieId2),
                generateMovieDetailsItem(movieId3)
            )
        }

        fun generateMovieDetailsItem(movieId: Int): MovieDetailsDataModel {
            return MovieDetailsDataModel(
                id = movieId,
                title = "Kung Fu Panda 4",
                overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                release_date = "2025-07-15",
                original_language = "en",
                popularity = 109.95,
                poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                vote_average = 7.5,
                vote_count = 2350,
                revenue = 360000000,
                status = "Released",
                adult = false,
                backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                budget = 50000000,
                homepage = "https://www.example.com",
                imdb_id = "tt1234567",
                original_title = "Kung Fu Panda 4",
                runtime = 95,
                tagline = "The legend continues...",
                video = false
            )
        }

        fun generateMovieDetailsLocalData(): MovieDetailsLocalEntity {
            return MovieDetailsLocalEntity(
                id = movieId1,
                title = "Kung Fu Panda 4",
                overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                release_date = "2025-07-15",
                original_language = "en",
                popularity = 109.95,
                poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                vote_average = 7.5,
                vote_count = 2350,
                revenue = 360000000,
                status = "Released",
                adult = false,
                backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                budget = 50000000,
                homepage = "https://www.example.com",
                imdb_id = "tt1234567",
                original_title = "Kung Fu Panda 4",
                runtime = 95,
                tagline = "The legend continues...",
                video = false
            )

        }
    }
}