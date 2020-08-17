package com.example.moviez.model.genre

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre>
)
