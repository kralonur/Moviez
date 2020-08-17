package com.example.moviez.model.tv

import com.example.moviez.model.cast.Cast
import com.example.moviez.model.crew.Crew
import com.squareup.moshi.Json

data class TVEpisodeDetails(
    @Json(name = "air_date") val airData: String,
    @Json(name = "crew") val crew: List<Crew>,
    @Json(name = "episode_number") val episodeNumber: Int,
    @Json(name = "guest_stars") val cast: List<Cast>,
    @Json(name = "name") val name: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "id") val id: Int,
    @Json(name = "production_code") val productionCode: String?,
    @Json(name = "season_number") val season_number: Int,
    @Json(name = "still_path") val still_path: String?,
    @Json(name = "vote_average") val vote_average: Double,
    @Json(name = "vote_count") val vote_count: Int
)
