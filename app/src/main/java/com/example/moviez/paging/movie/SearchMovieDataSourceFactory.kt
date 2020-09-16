package com.example.moviez.paging.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviez.model.movie.Movie
import com.example.moviez.repositories.SearchRepository
import kotlinx.coroutines.CoroutineScope

class SearchMovieDataSourceFactory(
    private val repository: SearchRepository,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Movie>() {
    private val source = MutableLiveData<SearchMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        SearchMovieDataSource(repository, query, scope).apply {
            this@SearchMovieDataSourceFactory.source.postValue(this)
            return this
        }
    }

    fun getQuery() = query

    fun getSource() = source.value

    fun updateQuery(query: String) {
        this.query = query
        getSource()?.refresh()
    }
}