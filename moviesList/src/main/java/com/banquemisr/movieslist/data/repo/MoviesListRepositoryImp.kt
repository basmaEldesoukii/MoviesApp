package com.banquemisr.movieslist.data.repo

import com.banquemisr.common.Mapper
import com.banquemisr.common.Resource
import com.banquemisr.movieslist.data.local.MovieLocalEntity
import com.banquemisr.movieslist.data.local.MoviesListLocalDataSourceContract
import com.banquemisr.movieslist.data.remote.MoviesListRemoteDataSourceContract
import com.banquemisr.movieslist.domain.contract.MoviesListRepositoryContract
import com.banquemisr.movieslist.domain.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesListRepositoryImp @Inject constructor(
    private val localDataSource: MoviesListLocalDataSourceContract,
    private val remoteDataSource: MoviesListRemoteDataSourceContract,
    private val moviesListDataMapper: Mapper<Movie, MovieLocalEntity>,
): MoviesListRepositoryContract {

    override suspend fun getNowPlayingList(): Flow<Resource<List<Movie>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getNowPlayingList()
                // Emit data
                emit(Resource.Success(data.results))
                // Save to local
                localDataSource.insertMoviesList(moviesListDataMapper.fromList(data.results))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val localData = localDataSource.getMoviesListFromDataBase()
                    // Emit data
                    emit(Resource.Success(moviesListDataMapper.toList(localData)))
                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }

    override suspend fun getPopularList(): Flow<Resource<List<Movie>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getPopularList()
                // Emit data
                emit(Resource.Success(data.results))
                // Save to local
                localDataSource.insertMoviesList(moviesListDataMapper.fromList(data.results))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val localData = localDataSource.getMoviesListFromDataBase()
                    // Emit data
                    emit(Resource.Success(moviesListDataMapper.toList(localData)))
                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }

    override suspend fun getUpcomingList(): Flow<Resource<List<Movie>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getUpcomingList()
                // Emit data
                emit(Resource.Success(data.results))
                // Save to local
                localDataSource.insertMoviesList(moviesListDataMapper.fromList(data.results))

            } catch (ex: Exception) {
                ex.printStackTrace()
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val localData = localDataSource.getMoviesListFromDataBase()
                    // Emit data
                    emit(Resource.Success(moviesListDataMapper.toList(localData)))
                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}