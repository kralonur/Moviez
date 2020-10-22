package com.example.moviez.paging.movie

import androidx.paging.PagingSource
import com.example.moviez.api.services.SearchService
import com.example.moviez.model.movie.Movie
import timber.log.Timber

class SearchMovieDataSource(
    private val searchService: SearchService,
    private var query: String,
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
            Timber.e(exception)
            LoadResult.Error(exception)
        }
    }

    private suspend fun loadPage(
        language: String? = null,
        page: Int,
        region: String? = null
    ): List<Movie> {
        Timber.i("Load page $page")
        val response = searchService.searchMovies(language, query, page, false, region, null, null)

        return response.results
    }

}