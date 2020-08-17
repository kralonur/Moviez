package com.example.moviez.model.image

import com.example.moviez.model.result.BaseResponseMarker
import com.squareup.moshi.Json

data class ImageDetails(
    @Json(name = "aspect_ratio") val aspectRatio: Double,
    @Json(name = "file_path") val filePath: String,
    @Json(name = "height") val height: Int,
    @Json(name = "iso_631_1") val iso_631_1: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "width") val width: Int
) : BaseResponseMarker
