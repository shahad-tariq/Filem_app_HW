package com.example.filem_app_hw.repository

import com.example.filem_app_hw.data.*
import com.example.filem_app_hw.util.Constants.KEY_API
import com.example.filem_app_hw.network.API
import com.example.filem_app_hw.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

object Repository {

    fun getMoviesPopular()
            : Flow<Status<PopularMoviesResponse?>> =
        wrampWithFlow { API.apiService.getMovieListPopular(KEY_API) }

    fun getMoviesTopRated(): Flow<Status<TopRatedResponse?>> =
        wrampWithFlow {API.apiService.getMovieListTopRated(KEY_API) }

    fun getMoviesTvAir(): Flow<Status<TvAirResponse?>> =
        wrampWithFlow {API.apiService.getMovieListTvAir(KEY_API) }

    fun getMoviesSearch(q: String): Flow<Status<SearchResponse?>> =
        wrampWithFlow {API.apiService.getMovieListSearch(KEY_API , q) }

    fun getMoviesDetails(moviesId: Int?): Flow<Status<DetailsResponse?>> =
        wrampWithFlow {API.apiService.getMovieDetails(moviesId!!, KEY_API) }

    fun getMoviesLatest(): Flow<Status<LatestResponse?>> =
        wrampWithFlow {API.apiService.getMovieLatest(KEY_API) }

    fun getMoviesSimilar(moviesId: Int?): Flow<Status<SimilarResponse?>> =
        wrampWithFlow {API.apiService.getMovieSimilar(moviesId!!, KEY_API) }

    fun getMoviesDiscover(): Flow<Status<DiscoverResponse?>> =
        wrampWithFlow {API.apiService.getMovieDiscover(KEY_API) }

    fun <T> wrampWithFlow(function: suspend () -> Response<T>): Flow<Status<T?>> =
        flow {
            emit(Status.Loading)
            try{
                val result = function()
                if (result.isSuccessful){
                    emit(Status.Success(result.body()))
                }
                else
                    emit(Status.Error(result.message()))
            } catch (e:Exception){
                emit(Status.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


}