package com.example.moviez.model.person

import com.example.moviez.model.result.BaseResponseMarker
import com.squareup.moshi.Json

data class Person(
    @Json(name = "id") val id: Int,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "name") val name: String,
    @Json(name = "popularity") val popularity: Double = 0.0,
    @Json(name = "known_for_department") val knowForDepartment: String?
) : BaseResponseMarker
