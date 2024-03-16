package com.banquemisr.movieslist.domain.usecase

import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract

class GetPopularListUseCase(
    private val moviesListRepositoryContract: MoviesListRepositoryContract
) {
    suspend operator fun invoke() = moviesListRepositoryContract.getPopularList()
}