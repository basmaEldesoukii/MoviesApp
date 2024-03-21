package com.banquemisr.movieslist.utils

import com.banquemisr.movieslist.data.local.MovieLocalEntity
import com.banquemisr.movieslist.domain.entity.Movie
import com.banquemisr.movieslist.domain.entity.MoviesListDataModel
import com.banquemisr.movieslist.presentation.viewmodel.MoviesListContract

class TestDataGenerator {
    companion object {

        const val movieId1 = 1
        private const val movieId2 = 2
        private const val movieId3 = 3


        // Data for UseCase Test
        fun generateMoviesList(): List<Movie> {
            return listOf(
                generateMovieItem(movieId1),
                generateMovieItem(movieId2),
                generateMovieItem(movieId3)
            )
        }

        fun generateMovieItem(movieId: Int): Movie {
            return Movie(
                id = movieId,
                title = "Kung Fu Panda 4",
                overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                release_date = "2025-07-15",
                original_language = "en",
                popularity = 109.95,
                poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                vote_average = 7.5,
                vote_count = 2350,
                adult = false,
                backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                original_title = "Kung Fu Panda 4",
                video = false
            )
        }
        //endOfRegion

        //Data for LocalData Test
        fun generateListOfLocalMovieItem(): List<MovieLocalEntity> {
            return listOf(
                generateLocalMovieItem(movieId1),
                generateLocalMovieItem(movieId2),
                generateLocalMovieItem(movieId3)
            )
        }

        fun generateLocalMovieItem(movieId: Int): MovieLocalEntity {
            return MovieLocalEntity(
                id = movieId,
                title = "Kung Fu Panda 4",
                overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                release_date = "2025-07-15",
                original_language = "en",
                popularity = 109.95,
                poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                vote_average = 7.5,
                vote_count = 2350,
                adult = false,
                backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                original_title = "Kung Fu Panda 4",
                video = false
            )
        }
        //endOfRegion

        //Data for RemoteData Test
        fun generateMoviesListResponse(): MoviesListDataModel {
            return MoviesListDataModel(
                page = 0,
                results = generateMoviesList(),
                total_pages = 3,
                total_results = 5,
            )
        }
        //endOfRegion

        fun generateDummyMoviesList(): MoviesListContract.MoviesList {
            val nowPlayingList = listOf(
                Movie(
                    id = movieId1,
                    title = "Kung Fu Panda 4",
                    overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                    release_date = "2025-07-15",
                    original_language = "en",
                    popularity = 109.95,
                    poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                    vote_average = 7.5,
                    vote_count = 2350,
                    adult = false,
                    backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                    original_title = "Kung Fu Panda 4",
                    video = false
                )
            )

            val popularList = listOf(
                Movie(
                    id = movieId1,
                    title = "Kung Fu Panda 4",
                    overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                    release_date = "2025-07-15",
                    original_language = "en",
                    popularity = 109.95,
                    poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                    vote_average = 7.5,
                    vote_count = 2350,
                    adult = false,
                    backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                    original_title = "Kung Fu Panda 4",
                    video = false
                )

            )

            val upcomingList = listOf(
                Movie(
                    id = movieId1,
                    title = "Kung Fu Panda 4",
                    overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis ex vel justo vehicula efficitur nec id lorem. Nulla facilisi. Donec malesuada elit nec tellus feugiat, in laoreet libero tincidunt. Phasellus vel dapibus turpis. Sed tristique dolor nec orci congue sollicitudin.",
                    release_date = "2025-07-15",
                    original_language = "en",
                    popularity = 109.95,
                    poster_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                    vote_average = 7.5,
                    vote_count = 2350,
                    adult = false,
                    backdrop_path = "https://image.tmdb.org/t/p/w500//hUu9zyZmDd8VZegKi1iK1Vk0RYS.jpg",
                    original_title = "Kung Fu Panda 4",
                    video = false
                )
            )

            return MoviesListContract.MoviesList(nowPlayingList, popularList, upcomingList)
        }
    }
}