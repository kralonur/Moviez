package com.example.moviez.model.image

import com.squareup.moshi.Json

data class Images(
    @Json(name = "id") val id: Int?,
    @Json(name = "backdrops") val backdrops: List<ImageDetails>,
    @Json(name = "posters") val posters: List<ImageDetails>
)
