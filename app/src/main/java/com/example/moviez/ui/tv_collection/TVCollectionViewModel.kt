package com.example.moviez.ui.tv_collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.enums.TVQueryType
import com.example.moviez.paging.tv.TVDataSourceFactory
import com.example.moviez.repositories.TVRepository
import com.example.moviez.repositories.TrendingRepository

class TVCollectionViewModel : ViewModel() {
    private val tvRepository = TVRepository()
    private val trendingRepository = TrendingRepository()

    fun tvList(queryType: TVQueryType) =
        LivePagedListBuilder(getDataSourceFactory(queryType), getPagedListConfig()).build()

    private fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .build()
    }

    private fun getDataSourceFactory(queryType: TVQueryType) =
        TVDataSourceFactory(tvRepository, trendingRepository, viewModelScope, queryType)

}
