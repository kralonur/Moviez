package com.example.moviez.repositories

import com.example.moviez.api.NetworkService
import com.example.moviez.model.movie.Movie
import com.example.moviez.model.person.Person
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result
import com.example.moviez.model.tv.TV

class TrendingRepository : BaseRepository() {
    private val api = NetworkService.trendingService

    private enum class MediaType(val path: String) {
        ALL("all"), MOVIE("movie"), TV("tv"), PERSON("person")
    }

    enum class TimeWindow(val path: String) {
        DAY("day"), WEEK("week")
    }

    suspend fun getTrendingMovies(
        timeWindow: TimeWindow,
        page: Int?,
        language: String?
    ): Result<BaseResponse<Movie>> {
        return coroutineApiCall(dispatcher) {
            api.getTrendingMovies(MediaType.MOVIE.path, timeWindow.path, page, language)
        }
    }

    suspend fun getTrendingTVs(
        timeWindow: TimeWindow,
        page: Int?,
        language: String?
    ): Result<BaseResponse<TV>> {
        return coroutineApiCall(dispatcher) {
            api.getTrendingTVs(MediaType.TV.path, timeWindow.path, page, language)
        }
    }

    suspend fun getTrendingPeoples(
        timeWindow: TimeWindow,
        page: Int?,
        language: String?
    ): Result<BaseResponse<Person>> {
        return coroutineApiCall(dispatcher) {
            api.getTrendingPeoples(MediaType.PERSON.path, timeWindow.path, page, language)
        }
    }
}