package com.example.moviez.ui.search.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.model.movie.Movie
import com.example.moviez.paging.movie.SearchMovieDataSourceFactory
import com.example.moviez.repositories.SearchRepository

class SearchMovieViewModel : ViewModel() {
    private val repository = SearchRepository()

    private val _navigateDetail = MutableLiveData<Movie?>()
    val navigateDetail: LiveData<Movie?>
        get() = _navigateDetail

    private val dataSourceFactory = SearchMovieDataSourceFactory(repository, scope = viewModelScope)

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

    fun navigateToDetail(movie: Movie) {
        _navigateDetail.postValue(movie)
    }

    fun navigateToDetailDone() {
        _navigateDetail.postValue(null)
    }
}