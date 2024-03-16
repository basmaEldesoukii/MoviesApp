package com.banquemisr.movie_details.domain.usecase

import com.banquemisr.movie_details.domain.contract.MovieDetailsRepositoryContract

class GetMovieDetailsUseCase(
    private val movieDetailsRepositoryContract: MovieDetailsRepositoryContract
){
    suspend operator fun invoke(id: Int) = movieDetailsRepositoryContract.getMovieDetails(id)
}