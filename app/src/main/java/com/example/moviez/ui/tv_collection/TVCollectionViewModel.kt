package com.example.moviez.ui.tv_collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviez.api.NetworkService
import com.example.moviez.enums.TVQueryType
import com.example.moviez.paging.tv.TVDataSource

class TVCollectionViewModel : ViewModel() {
    private val tvService = NetworkService.tvService
    private val trendingService = NetworkService.trendingService

    private fun getDataSource(queryType: TVQueryType) = TVDataSource(
        tvService,
        trendingService,
        queryType
    )

    private val pagingConfig =
        PagingConfig(pageSize = 20, initialLoadSize = 5, enablePlaceholders = false)

    fun getTvPagingFlow(queryType: TVQueryType) =
        Pager(pagingConfig) { getDataSource(queryType) }.flow.cachedIn(viewModelScope)

}
