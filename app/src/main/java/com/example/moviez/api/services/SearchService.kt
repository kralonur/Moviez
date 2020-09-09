package com.example.moviez.api.services

import com.example.moviez.model.movie.Movie
import com.example.moviez.model.person.Person
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.tv.TV
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("language") language: String?,
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("include_adult") includeAdult: Boolean?,
        @Query("region") region: String?,
        @Query("year") year: Int?,
        @Query("primary_release_year") primaryReleaseYear: Int?
    ): BaseResponse<Movie>

    @GET("search/tv")
    suspend fun searchTVSeries(
        @Query("language") language: String?,
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("first_air_date_year") firstAirDateYear: Int?
    ): BaseResponse<TV>


    @GET("search/person")
    suspend fun searchPeoples(
        @Query("language") language: String?,
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("include_adult") includeAdult: Boolean?,
        @Query("region") region: String?
    ): BaseResponse<Person>
}