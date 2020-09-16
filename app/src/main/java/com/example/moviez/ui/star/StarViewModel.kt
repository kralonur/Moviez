package com.example.moviez.ui.star

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviez.enums.PersonQueryType
import com.example.moviez.model.person.Person
import com.example.moviez.paging.person.PersonDataSourceFactory
import com.example.moviez.repositories.PersonRepository
import com.example.moviez.repositories.TrendingRepository

class StarViewModel : ViewModel() {

    private val personRepository = PersonRepository()
    private val trendingRepository = TrendingRepository()

    private val _navigateDetail = MutableLiveData<Person?>()
    val navigateDetail: LiveData<Person?>
        get() = _navigateDetail

    private val dataSourceFactory = PersonDataSourceFactory(
        personRepository, trendingRepository, viewModelScope, PersonQueryType.TRENDING_WEEKLY
    )

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(20)
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .build()


    val personList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    fun navigateToDetail(person: Person) {
        _navigateDetail.postValue(person)
    }

    fun navigateToDetailDone() {
        _navigateDetail.postValue(null)
    }

}