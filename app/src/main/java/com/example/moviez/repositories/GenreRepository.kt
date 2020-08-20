package com.example.moviez.repositories

import com.example.moviez.api.NetworkService
import com.example.moviez.model.genre.GenreResponse
import com.example.moviez.model.result.Result

class GenreRepository : BaseRepository() {
    private val api = NetworkService.genreService

    suspend fun getMovieGenres(language: String?): Result<GenreResponse> {
        return coroutineApiCall(dispatcher) { api.getMovieGenres(language) }
    }

    suspend fun getTVGenres(language: String?): Result<GenreResponse> {
        return coroutineApiCall(dispatcher) { api.getTVGenres(language) }
    }
}