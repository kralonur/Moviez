package com.example.moviez.paging.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviez.enums.NetworkState
import com.example.moviez.model.person.Person
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result
import com.example.moviez.repositories.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchPersonDataSource(
    private val repository: SearchRepository,
    private val query: String,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Person>() {

    private var supervisorJob = SupervisorJob()
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState
    private var retryQuery: (() -> Any)? = null


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Person>
    ) {
        retryQuery = { loadInitial(params, callback) }
        load(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
        retryQuery = { loadAfter(params, callback) }
        val page = params.key
        load(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
        retryQuery = { loadAfter(params, callback) }
        val page = params.key
        load(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun load(
        page: Int,
        callback: (List<Person>) -> Unit
    ) {
        Timber.i("Load page $page")
        _networkState.postValue(NetworkState.LOADING)
        scope.launch(getExceptionHandler() + supervisorJob) {
            val response = loadPage(null, page, null)
            retryQuery = null
            _networkState.postValue(NetworkState.SUCCESS)
            response?.let { callback(it) }
        }
    }

    private fun getExceptionHandler() = CoroutineExceptionHandler { _, e ->
        Timber.e(e)
        _networkState.postValue(NetworkState.FAIL)
    }

    private suspend fun loadPage(
        language: String?,
        page: Int,
        region: String?
    ): List<Person>? {
        val response = repository.searchPeoples(language, query, page, null, region)

        return getResult(response)
    }

    private fun getResult(response: Result<BaseResponse<Person>>): List<Person> {
        return when (response) {
            is Result.Success -> response.value.results
            else -> emptyList()
        }

    }

    fun refresh() = this.invalidate()
}