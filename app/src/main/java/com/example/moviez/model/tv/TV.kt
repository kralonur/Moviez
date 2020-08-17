package com.example.moviez.model.tv

import com.example.moviez.model.result.BaseResponseMarker
import com.squareup.moshi.Json

data class TV(
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "id") val id: Int,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "overview") val overview: String,
    @Json(name = "first_air_date") val firstAirDate: String?,
    @Json(name = "origin_country") val originCountry: List<String>,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "name") val name: String,
    @Json(name = "original_name") val originalName: String
) : BaseResponseMarker
