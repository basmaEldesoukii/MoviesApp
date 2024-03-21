package com.banquemisr.movieslist.data.repo

import com.banquemisr.common.Mapper
import com.banquemisr.movieslist.data.local.MovieLocalEntity
import com.banquemisr.movieslist.domain.entity.Movie
import javax.inject.Inject

class MoviesListDataMapper @Inject constructor() :
    Mapper<Movie, MovieLocalEntity> {

    override fun from(i: Movie?): MovieLocalEntity {
        return MovieLocalEntity(
            id = i?.id ?: 0,
            title = i?.title ?: "",
            adult = i?.adult ?: false,
            backdrop_path = i?.backdrop_path ?: "",
            original_language = i?.original_language ?: "",
            original_title = i?.original_title ?: "",
            overview = i?.overview ?: "",
            popularity = i?.popularity ?: 0.0,
            poster_path = i?.poster_path ?: "",
            release_date = i?.release_date ?: "",
            video = i?.video ?: false,
            vote_average = i?.vote_average ?: 0.0,
            vote_count = i?.vote_count ?: 0

        )
    }

    override fun to(o: MovieLocalEntity?): Movie {
        return Movie(
            id = o?.id ?: 0,
            title = o?.title ?: "",
            adult = o?.adult ?: false,
            backdrop_path = o?.backdrop_path ?: "",
            original_language = o?.original_language ?: "",
            original_title = o?.original_title ?: "",
            overview = o?.overview ?: "",
            popularity = o?.popularity ?: 0.0,
            poster_path = o?.poster_path ?: "",
            release_date = o?.release_date ?: "",
            video = o?.video ?: false,
            vote_average = o?.vote_average ?: 0.0,
            vote_count = o?.vote_count ?: 0

        )
    }
}