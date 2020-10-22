package com.example.moviez.paging.person

import androidx.paging.PagingSource
import com.example.moviez.api.services.SearchService
import com.example.moviez.model.person.Person
import timber.log.Timber

class SearchPersonDataSource(
    private val searchService: SearchService,
    private var query: String,
) : PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
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
    ): List<Person> {
        Timber.i("Load page $page")
        val response = searchService.searchPeoples(language, query, page, null, region)

        return response.results
    }
}