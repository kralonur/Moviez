package com.example.moviez.model.lang

import com.squareup.moshi.Json

data class SpokenLanguage(
    @Json(name = "iso_639_1") val iso6391: String,
    @Json(name = "name") val name: String
)
