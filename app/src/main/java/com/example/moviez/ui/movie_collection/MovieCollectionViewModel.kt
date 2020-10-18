package com.example.moviez.ui.movie_collection

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.paging.movie.MovieDataSourceFactory
import com.example.moviez.repositories.MovieRepository
import com.example.moviez.repositories.TrendingRepository

class MovieCollectionViewModel(queryType: MovieQueryType) : ViewModel() {
    private val movieRepository = MovieRepository()
    private val trendingRepository = TrendingRepository()

    private val dataSourceFactory =
        MovieDataSourceFactory(movieRepository, trendingRepository, viewModelScope, queryType)

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .build()

    val movieList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    @Suppress("UNCHECKED_CAST")
    class Factory(private val queryType: MovieQueryType) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MovieCollectionViewModel::class.java)) {
                MovieCollectionViewModel(queryType) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}