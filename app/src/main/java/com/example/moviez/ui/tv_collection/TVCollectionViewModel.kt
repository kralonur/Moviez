package com.example.moviez.ui.tv_collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.enums.TVQueryType
import com.example.moviez.paging.tv.TVDataSourceFactory
import com.example.moviez.repositories.TVRepository
import com.example.moviez.repositories.TrendingRepository

class TVCollectionViewModel(queryType: TVQueryType) : ViewModel() {
    private val tvRepository = TVRepository()
    private val trendingRepository = TrendingRepository()

    private val dataSourceFactory =
        TVDataSourceFactory(tvRepository, trendingRepository, viewModelScope, queryType)

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .build()

    val tvList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    @Suppress("UNCHECKED_CAST")
    class Factory(private val queryType: TVQueryType) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(TVCollectionViewModel::class.java)) {
                TVCollectionViewModel(queryType) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}