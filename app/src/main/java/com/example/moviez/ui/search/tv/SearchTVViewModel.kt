package com.example.moviez.ui.search.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.paging.tv.SearchTVDataSourceFactory
import com.example.moviez.repositories.SearchRepository

class SearchTVViewModel : ViewModel() {
    private val repository = SearchRepository()

    private val dataSourceFactory = SearchTVDataSourceFactory(repository, scope = viewModelScope)

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .build()

    val searchResults = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    fun updateQuery(query: String) {
        query.trim().apply {
            if (dataSourceFactory.getQuery() == this) return
            dataSourceFactory.updateQuery(this)
        }
    }

}