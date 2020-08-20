package com.example.moviez.repositories

import com.example.moviez.api.NetworkService
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result
import com.example.moviez.model.tv.TV
import com.example.moviez.model.tv.TVDetails
import com.example.moviez.model.tv.TVEpisodeDetails
import com.example.moviez.model.tv.TVSeasonDetails

class TVRepository : BaseRepository() {
    private val api = NetworkService.tvService

    suspend fun getTVById(
        tvId: Int,
        language: String?,
        appendToResponse: String?,
        imageLanguages: String?
    ): Result<TVDetails> {
        return coroutineApiCall(dispatcher) {
            api.getTVById(
                tvId,
                language,
                appendToResponse,
                imageLanguages
            )
        }
    }

    suspend fun getPopularTVs(
        language: String?,
        page: Int?
    ): Result<BaseResponse<TV>> {
        return coroutineApiCall(dispatcher) { api.getPopularTVs(language, page) }
    }

    suspend fun getTopRatedTVs(
        language: String?,
        page: Int?
    ): Result<BaseResponse<TV>> {
        return coroutineApiCall(dispatcher) { api.getTopRatedTVs(language, page) }
    }


    suspend fun getAiringTodayTVs(language: String?, page: Int?): Result<BaseResponse<TV>> {
        return coroutineApiCall(dispatcher) {
            api.getAiringTodayTVs(language, page)
        }
    }

    suspend fun getOnTheAirTVs(language: String?, page: Int?): Result<BaseResponse<TV>> {
        return coroutineApiCall(dispatcher) {
            api.getOnTheAirTVs(language, page)
        }
    }

    suspend fun getSeasonDetails(
        tvId: Int,
        seasonNumber: Int,
        language: String?,
        appendToResponse: String?
    ): Result<TVSeasonDetails> {
        return coroutineApiCall(dispatcher) {
            api.getSeasonDetails(
                tvId,
                seasonNumber,
                language,
                appendToResponse
            )
        }
    }

    suspend fun getEpisodeDetails(
        tvId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        language: String?,
        appendToResponse: String?
    ): Result<TVEpisodeDetails> {
        return coroutineApiCall(dispatcher) {
            api.getEpisodeDetails(
                tvId,
                seasonNumber,
                episodeNumber,
                language,
                appendToResponse
            )
        }
    }
}