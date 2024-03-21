package com.banquemisr.movie_details.data.repo

import com.banquemisr.common.Mapper
import com.banquemisr.movie_details.data.local.MovieDetailsLocalEntity
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import javax.inject.Inject

class MovieDetailsDataMapper @Inject constructor() :
    Mapper<MovieDetailsDataModel, MovieDetailsLocalEntity> {

    override fun from(i: MovieDetailsDataModel?): MovieDetailsLocalEntity {
        return MovieDetailsLocalEntity(
            id = i?.id ?: 0,
            title = i?.title ?: "",
            adult = i?.adult ?: false,
            backdrop_path = i?.backdrop_path ?: "",
            budget = i?.budget ?: 0,
            homepage = i?.homepage ?: "",
            imdb_id = i?.imdb_id ?: "",
            original_language = i?.original_language ?: "",
            original_title = i?.original_title ?: "",
            overview = i?.overview ?: "",
            popularity = i?.popularity ?: 0.0,
            poster_path = i?.poster_path ?: "",
            release_date = i?.release_date ?: "",
            revenue = i?.revenue ?: 0,
            runtime = i?.runtime ?: 0,
            status = i?.status ?: "",
            tagline = i?.tagline ?: "",
            video = i?.video ?: false,
            vote_average = i?.vote_average ?: 0.0,
            vote_count = i?.vote_count ?: 0

        )
    }

    override fun to(o: MovieDetailsLocalEntity?): MovieDetailsDataModel {
        return MovieDetailsDataModel(
            id = o?.id ?: 0,
            title = o?.title ?: "",
            adult = o?.adult ?: false,
            backdrop_path = o?.backdrop_path ?: "",
            budget = o?.budget ?: 0,
            homepage = o?.homepage ?: "",
            imdb_id = o?.imdb_id ?: "",
            original_language = o?.original_language ?: "",
            original_title = o?.original_title ?: "",
            overview = o?.overview ?: "",
            popularity = o?.popularity ?: 0.0,
            poster_path = o?.poster_path ?: "",
            release_date = o?.release_date ?: "",
            revenue = o?.revenue ?: 0,
            runtime = o?.runtime ?: 0,
            status = o?.status ?: "",
            tagline = o?.tagline ?: "",
            video = o?.video ?: false,
            vote_average = o?.vote_average ?: 0.0,
            vote_count = o?.vote_count ?: 0
        )
    }
}