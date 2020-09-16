package com.example.moviez.ui.tv_collection

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.tv.TV
import com.example.moviez.paging.tv.TVDataSourceFactory
import com.example.moviez.repositories.TVRepository
import com.example.moviez.repositories.TrendingRepository

class TVCollectionViewModel(queryType: TVQueryType) : ViewModel() {
    private val tvRepository = TVRepository()
    private val trendingRepository = TrendingRepository()

    private val _navigateDetail = MutableLiveData<TV?>()
    val navigateDetail: LiveData<TV?>
        get() = _navigateDetail

    private val dataSourceFactory =
        TVDataSourceFactory(tvRepository, trendingRepository, viewModelScope, queryType)

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .build()

    val tvList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    fun navigateToDetail(tv: TV) {
        _navigateDetail.postValue(tv)
    }

    fun navigateToDetailDone() {
        _navigateDetail.postValue(null)
    }

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