package com.example.moviez.model.movie

import com.example.moviez.model.result.BaseResponseMarker
import com.squareup.moshi.Json

data class Movie(
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "id") val id: Int,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "title") val title: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double

) : BaseResponseMarker
