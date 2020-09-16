package com.example.moviez.ui.search.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.model.tv.TV
import com.example.moviez.paging.tv.SearchTVDataSourceFactory
import com.example.moviez.repositories.SearchRepository

class SearchTVViewModel : ViewModel() {
    private val repository = SearchRepository()

    private val _navigateDetail = MutableLiveData<TV?>()
    val navigateDetail: LiveData<TV?>
        get() = _navigateDetail

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

    fun navigateToDetail(tv: TV) {
        _navigateDetail.postValue(tv)
    }

    fun navigateToDetailDone() {
        _navigateDetail.postValue(null)
    }
}