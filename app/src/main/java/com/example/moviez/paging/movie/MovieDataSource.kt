package com.example.moviez.paging.movie

import androidx.paging.PagingSource
import com.example.moviez.api.services.MovieService
import com.example.moviez.api.services.TrendingService
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import timber.log.Timber

class MovieDataSource(
    private val movieService: MovieService,
    private val trendingService: TrendingService,
    private val queryType: MovieQueryType
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = loadPage(page = nextPageNumber)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (exception: Throwable) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun loadPage(
        language: String? = null,
        page: Int,
        region: String? = null
    ): List<Movie> {
        Timber.i("Load page $page")
        val response = when (queryType) {
            MovieQueryType.POPULAR -> {
                movieService.getPopularMovies(language, page, region)
            }
            MovieQueryType.TOP_RATED -> {
                movieService.getTopRatedMovies(language, page, region)
            }
            MovieQueryType.UPCOMING -> {
                movieService.getUpcomingMovies(language, page, region)
            }
            MovieQueryType.NOW_PLAYING -> {
                movieService.getNowPlayingMovies(language, page, region)
            }
            MovieQueryType.TRENDING_DAILY -> {
                trendingService.getTrendingMovies(
                    "movie",
                    "day",
                    page,
                    language
                )
            }
            MovieQueryType.TRENDING_WEEKLY -> {
                trendingService.getTrendingMovies(
                    "movie",
                    "week",
                    page,
                    language
                )
            }
        }

        return response.results
    }
}
