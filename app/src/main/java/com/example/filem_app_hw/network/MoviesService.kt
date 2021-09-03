package com.example.filem_app_hw.network

import com.example.filem_app_hw.data.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("3/movie/popular")
    suspend fun getMovieListPopular(
        @Query("api_key") apiKey: String
    ): Response<PopularMoviesResponse>

    @GET("3/movie/top_rated")
    suspend fun getMovieListTopRated(
        @Query("api_key") apiKey: String,
    ): Response<TopRatedResponse>

    @GET("3/tv/on_the_air")
    suspend fun getMovieListTvAir(
        @Query("api_key") apiKey: String,
    ): Response<TvAirResponse>

    @GET("3/search/multi")
    suspend fun getMovieListSearch(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<SearchResponse>

    @GET("3/movie/latest")
    suspend fun getMovieLatest(
        @Query("api_key") apiKey: String
    ): Response<LatestResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailsResponse>

    @GET("3/movie/{movie_id}/similar")
    suspend fun getMovieSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<SimilarResponse>

    @GET("3/discover/movie")
    suspend fun getMovieDiscover(
        @Query("api_key") apiKey: String
    ): Response<DiscoverResponse>

}