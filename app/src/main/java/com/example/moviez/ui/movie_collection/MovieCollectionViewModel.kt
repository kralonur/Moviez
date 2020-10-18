package com.example.moviez.ui.movie_collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.paging.movie.MovieDataSourceFactory
import com.example.moviez.repositories.MovieRepository
import com.example.moviez.repositories.TrendingRepository

class MovieCollectionViewModel : ViewModel() {
    private val movieRepository = MovieRepository()
    private val trendingRepository = TrendingRepository()

    fun movieList(queryType: MovieQueryType) =
        LivePagedListBuilder(getDataSourceFactory(queryType), getPagedListConfig()).build()

    private fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .build()
    }

    private fun getDataSourceFactory(queryType: MovieQueryType) =
        MovieDataSourceFactory(movieRepository, trendingRepository, viewModelScope, queryType)

}