package com.example.moviez.repositories

import com.example.moviez.api.NetworkService
import com.example.moviez.model.movie.Movie
import com.example.moviez.model.person.Person
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result
import com.example.moviez.model.tv.TV


class SearchRepository : BaseRepository() {
    private val api = NetworkService.searchService

    suspend fun searchMovies(
        language: String?,
        query: String,
        page: Int?,
        includeAdult: Boolean?,
        region: String?,
        year: Int?,
        primaryReleaseYear: Int?
    ): Result<BaseResponse<Movie>> {
        return coroutineApiCall(dispatcher) {
            api.searchMovies(
                language,
                query,
                page,
                includeAdult,
                region,
                year,
                primaryReleaseYear
            )
        }
    }

    suspend fun searchTVs(
        language: String?,
        query: String,
        page: Int?,
        firstAirDateYear: Int?
    ): Result<BaseResponse<TV>> {
        return coroutineApiCall(dispatcher) {
            api.searchTVSeries(language, query, page, firstAirDateYear)
        }
    }

    suspend fun searchPeoples(
        language: String?,
        query: String,
        page: Int?,
        includeAdult: Boolean?,
        region: String?
    ): Result<BaseResponse<Person>> {
        return coroutineApiCall(dispatcher) {
            api.searchPeoples(language, query, page, includeAdult, region)
        }
    }
}