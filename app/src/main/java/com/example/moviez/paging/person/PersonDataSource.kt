package com.example.moviez.paging.person

import androidx.paging.PagingSource
import com.example.moviez.api.services.PersonService
import com.example.moviez.api.services.TrendingService
import com.example.moviez.enums.PersonQueryType
import com.example.moviez.model.person.Person
import timber.log.Timber

class PersonDataSource(
    private val personService: PersonService,
    private val trendingService: TrendingService,
    private val queryType: PersonQueryType
) : PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = loadPage(null, nextPageNumber)

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
        language: String?,
        page: Int
    ): List<Person> {
        Timber.i("Load page $page")
        val response = when (queryType) {
            PersonQueryType.POPULAR -> {
                personService.getPopularPeoples(language, page)
            }
            PersonQueryType.TRENDING_DAILY -> {
                trendingService.getTrendingPeoples(
                    "person",
                    "day",
                    page,
                    language
                )
            }
            PersonQueryType.TRENDING_WEEKLY -> {
                trendingService.getTrendingPeoples(
                    "person",
                    "week",
                    page,
                    language
                )
            }
        }

        return response.results
    }

}