package com.example.moviez.paging.tv

import androidx.paging.PagingSource
import com.example.moviez.api.services.SearchService
import com.example.moviez.model.tv.TV
import timber.log.Timber

class SearchTVDataSource(
    private val searchService: SearchService,
    private var query: String,
) : PagingSource<Int, TV>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TV> {
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
        page: Int
    ): List<TV> {
        Timber.i("Load page $page")
        val response = searchService.searchTVSeries(language, query, page, null)

        return response.results
    }

}