package com.example.moviez.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query


    fun updateQuery(query: String) {
        _query.postValue(query)
    }
}