package com.example.moviez.ui.movie_collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviez.api.NetworkService
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.paging.movie.MovieDataSource

class MovieCollectionViewModel : ViewModel() {
    private val movieService = NetworkService.movieService
    private val trendingService = NetworkService.trendingService

    private fun getDataSource(queryType: MovieQueryType) = MovieDataSource(
        movieService,
        trendingService,
        queryType
    )

    private val pagingConfig =
        PagingConfig(pageSize = 20, initialLoadSize = 5, enablePlaceholders = false)

    fun getMoviePagingFlow(queryType: MovieQueryType) =
        Pager(pagingConfig) { getDataSource(queryType) }.flow.cachedIn(viewModelScope)

}