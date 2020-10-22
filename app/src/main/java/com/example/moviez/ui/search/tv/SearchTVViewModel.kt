package com.example.moviez.ui.search.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviez.api.NetworkService
import com.example.moviez.paging.tv.SearchTVDataSource

class SearchTVViewModel : ViewModel() {
    private val searchService = NetworkService.searchService

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    private fun getDataSource(query: String) = SearchTVDataSource(
        searchService,
        query
    )

    private val pagingConfig =
        PagingConfig(pageSize = 20, initialLoadSize = 5, enablePlaceholders = false)

    fun getTvPagingFlow(query: String) =
        Pager(pagingConfig) { getDataSource(query) }.flow.cachedIn(viewModelScope)

    fun updateQuery(query: String) {
        if (query == _query.value) return
        if (query.isNotEmpty() && query.isNotBlank()) {
            query.trim().apply {
                _query.postValue(query)
            }
        }
    }
}