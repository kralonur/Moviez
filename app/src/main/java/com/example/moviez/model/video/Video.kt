package com.example.moviez.model.video

import com.squareup.moshi.Json

data class Video(
    @Json(name = "id") val id: String,
    @Json(name = "iso_639_1") val iso_639_1: String,
    @Json(name = "iso_3166_1") val iso_3166_1: String,
    @Json(name = "key") val key: String,
    @Json(name = "name") val name: String,
    @Json(name = "site") val site: String,
    @Json(name = "size") val size: Int,
    @Json(name = "type") val type: String
)
