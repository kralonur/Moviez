package com.example.moviez.repositories

import com.example.moviez.api.NetworkService
import com.example.moviez.model.movie.Movie
import com.example.moviez.model.movie.MovieDetails
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result

class MovieRepository : BaseRepository() {
    private val api = NetworkService.movieService

    suspend fun getMovieById(
        movieId: Int,
        language: String?,
        appendToResponse: String?,
        imageLanguages: String?
    ): Result<MovieDetails> {
        return coroutineApiCall(dispatcher) {
            api.getMovieById(movieId, language, appendToResponse, imageLanguages)
        }
    }

    suspend fun getPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Result<BaseResponse<Movie>> {
        return coroutineApiCall(dispatcher) { api.getPopularMovies(language, page, region) }
    }

    suspend fun getTopRatedMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Result<BaseResponse<Movie>> {
        return coroutineApiCall(dispatcher) { api.getTopRatedMovies(language, page, region) }
    }


    suspend fun getUpcomingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Result<BaseResponse<Movie>> {
        return coroutineApiCall(dispatcher) {
            api.getUpcomingMovies(language, page, region)
        }
    }

    suspend fun getNowPlayingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): Result<BaseResponse<Movie>> {
        return coroutineApiCall(dispatcher) {
            api.getNowPlayingMovies(language, page, region)
        }
    }

}