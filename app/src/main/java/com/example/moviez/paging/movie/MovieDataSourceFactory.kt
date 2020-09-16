package com.example.moviez.paging.movie

import androidx.paging.DataSource
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.repositories.MovieRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.CoroutineScope

class MovieDataSourceFactory(
    private val movieRepository: MovieRepository,
    private val trendingRepository: TrendingRepository,
    private val scope: CoroutineScope,
    private val queryType: MovieQueryType
) : DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> {
        return MovieDataSource(
            movieRepository, trendingRepository, scope, queryType
        )
    }
}