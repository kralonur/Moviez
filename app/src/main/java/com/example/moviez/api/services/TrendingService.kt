package com.example.moviez.api.services

import com.example.moviez.model.movie.Movie
import com.example.moviez.model.person.Person
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.tv.TV
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingService {
    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int?,
        @Query("language") language: String?
    ): BaseResponse<Movie>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingTVs(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int?,
        @Query("language") language: String?
    ): BaseResponse<TV>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingPeoples(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int?,
        @Query("language") language: String?
    ): BaseResponse<Person>
}