package com.example.moviez.util

object Constants {
    object TMDB {
        const val BASE_URL = "https://api.themoviedb.org/3/"

        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

        const val IMAGE_SIZE_ORIGINAL = IMAGE_BASE_URL + "original"

        const val BACKDROP_SIZE_300 = IMAGE_BASE_URL + "w300"
        const val BACKDROP_SIZE_780 = IMAGE_BASE_URL + "w780"
        const val BACKDROP_SIZE_1280 = IMAGE_BASE_URL + "w1280"

        const val LOGO_SIZE_45 = IMAGE_BASE_URL + "w45"
        const val LOGO_SIZE_92 = IMAGE_BASE_URL + "w92"
        const val LOGO_SIZE_154 = IMAGE_BASE_URL + "w154"
        const val LOGO_SIZE_185 = IMAGE_BASE_URL + "w185"
        const val LOGO_SIZE_300 = IMAGE_BASE_URL + "w300"
        const val LOGO_SIZE_500 = IMAGE_BASE_URL + "w500"

        const val POSTER_SIZE_92 = IMAGE_BASE_URL + "w92"
        const val POSTER_SIZE_154 = IMAGE_BASE_URL + "w154"
        const val POSTER_SIZE_185 = IMAGE_BASE_URL + "w185"
        const val POSTER_SIZE_342 = IMAGE_BASE_URL + "w342"
        const val POSTER_SIZE_500 = IMAGE_BASE_URL + "w500"
        const val POSTER_SIZE_780 = IMAGE_BASE_URL + "w780"

        const val PROFILE_SIZE_45 = IMAGE_BASE_URL + "w45"
        const val PROFILE_SIZE_185 = IMAGE_BASE_URL + "w185"
        const val PROFILE_SIZE_632 = IMAGE_BASE_URL + "H632"

        const val STILL_SIZE_92 = IMAGE_BASE_URL + "w92"
        const val STILL_SIZE_185 = IMAGE_BASE_URL + "w185"
        const val STILL_SIZE_300 = IMAGE_BASE_URL + "w300"
    }
}