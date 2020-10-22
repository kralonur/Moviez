package com.example.moviez.paging.tv

import androidx.paging.PagingSource
import com.example.moviez.api.services.TVService
import com.example.moviez.api.services.TrendingService
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.tv.TV
import timber.log.Timber

class TVDataSource(
    private val tvService: TVService,
    private val trendingService: TrendingService,
    private val queryType: TVQueryType
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
            LoadResult.Error(exception)
        }
    }

    private suspend fun loadPage(
        language: String? = null,
        page: Int
    ): List<TV> {
        Timber.i("Load page $page")
        val response = when (queryType) {
            TVQueryType.POPULAR -> {
                tvService.getPopularTVs(language, page)
            }
            TVQueryType.TOP_RATED -> {
                tvService.getTopRatedTVs(language, page)
            }
            TVQueryType.AIRING_TODAY -> {
                tvService.getAiringTodayTVs(language, page)
            }
            TVQueryType.ON_THE_AIR -> {
                tvService.getOnTheAirTVs(language, page)
            }
            TVQueryType.TRENDING_DAILY -> {
                trendingService.getTrendingTVs(
                    "tv",
                    "day",
                    page,
                    language
                )
            }
            TVQueryType.TRENDING_WEEKLY -> {
                trendingService.getTrendingTVs(
                    "tv",
                    "week",
                    page,
                    language
                )
            }
        }

        return response.results
    }

}