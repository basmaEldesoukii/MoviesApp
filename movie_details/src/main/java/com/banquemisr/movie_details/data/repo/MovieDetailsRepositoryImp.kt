package com.banquemisr.movie_details.data.repo

import com.banquemisr.common.Mapper
import com.banquemisr.common.Resource
import com.banquemisr.movie_details.data.local.MovieDetailsLocalDataSourceContract
import com.banquemisr.movie_details.data.local.MovieDetailsLocalEntity
import com.banquemisr.movie_details.data.remote.MovieDetailsRemoteDataSourceContract
import com.banquemisr.movie_details.domain.contract.MovieDetailsRepositoryContract
import com.banquemisr.movie_details.domain.entity.MovieDetailsDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsRepositoryImp @Inject constructor(
    private val localDataSource: MovieDetailsLocalDataSourceContract,
    private val remoteDataSource: MovieDetailsRemoteDataSourceContract,
    private val movieDetailsDataMapper: Mapper<MovieDetailsDataModel, MovieDetailsLocalEntity>,
): MovieDetailsRepositoryContract {


    override suspend fun getMovieDetails(id: Int): Flow<Resource<MovieDetailsDataModel>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getMovieDetails(id)
                // Emit data
                emit(Resource.Success(data))
                // Save to local
                localDataSource.insertMovieDetails(movieDetailsDataMapper.from(data))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val localData = localDataSource.getMovieDetailsByIdFromDataBase(id)
                    // Emit data
                    emit(Resource.Success(movieDetailsDataMapper.to(localData)))
                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}