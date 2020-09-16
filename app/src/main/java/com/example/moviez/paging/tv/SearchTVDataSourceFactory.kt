package com.example.moviez.paging.tv

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviez.model.tv.TV
import com.example.moviez.repositories.SearchRepository
import kotlinx.coroutines.CoroutineScope

class SearchTVDataSourceFactory(
    private val repository: SearchRepository,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, TV>() {
    private val source = MutableLiveData<SearchTVDataSource>()

    override fun create(): DataSource<Int, TV> {
        SearchTVDataSource(repository, query, scope).apply {
            this@SearchTVDataSourceFactory.source.postValue(this)
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