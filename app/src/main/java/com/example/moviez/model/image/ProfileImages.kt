package com.example.moviez.model.image

import com.squareup.moshi.Json

data class ProfileImages(
    @Json(name = "id") val id: Int?,
    @Json(name = "profiles") val profiles: List<ImageDetails>
)
