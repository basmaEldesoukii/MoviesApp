package com.banquemisr.movieslist.domain.usecase

import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract

class GetNowPlayingListUseCase(
    private val moviesListRepositoryContract: MoviesListRepositoryContract
) {
    suspend operator fun invoke() = moviesListRepositoryContract.getNowPlayingList()
}
