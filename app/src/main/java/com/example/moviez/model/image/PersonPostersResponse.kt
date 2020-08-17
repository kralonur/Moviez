package com.example.moviez.model.image

import com.squareup.moshi.Json

data class PersonPostersResponse(
    @Json(name = "profiles") val profiles: List<ImageDetails>
)
