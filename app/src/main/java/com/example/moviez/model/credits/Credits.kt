package com.example.moviez.model.credits

import com.example.moviez.model.crew.Crew
import com.example.moviez.model.cast.Cast
import com.squareup.moshi.Json

data class Credits(
    @Json(name = "id") val id: Int?,
    @Json(name = "cast") val casts: List<Cast>,
    @Json(name = "crew") val crews: List<Crew>
)
