package com.example.moviez.api.services

import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.tv.TV
import com.example.moviez.model.tv.TVDetails
import com.example.moviez.model.tv.TVEpisodeDetails
import com.example.moviez.model.tv.TVSeasonDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVService {
    @GET("tv/{tv_id}")
    suspend fun getTVById(
        @Path("tv_id") tvId: Int,
        @Query("language") language: String?,
        @Query("append_to_response") appendToResponse: String?,
        @Query("include_image_language") imageLanguages: String?
    ): TVDetails

    @GET("tv/popular")
    suspend fun getPopularTVs(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): BaseResponse<TV>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVs(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): BaseResponse<TV>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTVs(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): BaseResponse<TV>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVs(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): BaseResponse<TV>

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") language: String?,
        @Query("append_to_response") appendToResponse: String?
    ): TVSeasonDetails

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    suspend fun getEpisodeDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int,
        @Query("language") language: String?,
        @Query("append_to_response") appendToResponse: String?
    ): TVEpisodeDetails
}