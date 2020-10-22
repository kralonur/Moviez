package com.example.moviez.ui.star

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviez.api.NetworkService
import com.example.moviez.enums.PersonQueryType
import com.example.moviez.paging.person.PersonDataSource

class StarViewModel : ViewModel() {
    private val personService = NetworkService.personService
    private val trendingService = NetworkService.trendingService

    private val dataSource = PersonDataSource(
        personService,
        trendingService,
        PersonQueryType.TRENDING_WEEKLY
    )

    private val pagingConfig =
        PagingConfig(pageSize = 20, initialLoadSize = 5, enablePlaceholders = false)

    val starPagingFlow = Pager(pagingConfig) { dataSource }.flow.cachedIn(viewModelScope)
}