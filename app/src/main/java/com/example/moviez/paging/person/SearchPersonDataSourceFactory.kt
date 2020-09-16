package com.example.moviez.paging.person

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviez.model.person.Person
import com.example.moviez.repositories.SearchRepository
import kotlinx.coroutines.CoroutineScope

class SearchPersonDataSourceFactory(
    private val repository: SearchRepository,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Person>() {
    private val source = MutableLiveData<SearchPersonDataSource>()

    override fun create(): DataSource<Int, Person> {
        SearchPersonDataSource(repository, query, scope).apply {
            this@SearchPersonDataSourceFactory.source.postValue(this)
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