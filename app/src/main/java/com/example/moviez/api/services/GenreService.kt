package com.example.moviez.api.services

import com.example.moviez.model.genre.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String?
    ): GenreResponse

    @GET("genre/tv/list")
    suspend fun getTVGenres(
        @Query("language") language: String?
    ): GenreResponse
}