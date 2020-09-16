package com.example.moviez.paging.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.enums.NetworkState
import com.example.moviez.model.movie.Movie
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result
import com.example.moviez.repositories.MovieRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDataSource(
    private val movieRepository: MovieRepository,
    private val trendingRepository: TrendingRepository,
    private val scope: CoroutineScope,
    private val queryType: MovieQueryType
) : PageKeyedDataSource<Int, Movie>() {

    private var supervisorJob = SupervisorJob()
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState
    private var retryQuery: (() -> Any)? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        retryQuery = { loadInitial(params, callback) }
        load(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        retryQuery = { loadAfter(params, callback) }
        val page = params.key
        load(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        retryQuery = { loadAfter(params, callback) }
        val page = params.key
        load(page) {
            callback.onResult(it, page - 1)
        }
    }

    private fun load(
        page: Int,
        callback: (List<Movie>) -> Unit
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
    ): List<Movie>? {
        val response = when (queryType) {
            MovieQueryType.POPULAR -> {
                movieRepository.getPopularMovies(language, page, region)
            }
            MovieQueryType.TOP_RATED -> {
                movieRepository.getTopRatedMovies(language, page, region)
            }
            MovieQueryType.UPCOMING -> {
                movieRepository.getUpcomingMovies(language, page, region)
            }
            MovieQueryType.NOW_PLAYING -> {
                movieRepository.getNowPlayingMovies(language, page, region)
            }
            MovieQueryType.TRENDING_DAILY -> {
                trendingRepository.getTrendingMovies(
                    TrendingRepository.TimeWindow.DAY,
                    page,
                    language
                )
            }
            MovieQueryType.TRENDING_WEEKLY -> {
                trendingRepository.getTrendingMovies(
                    TrendingRepository.TimeWindow.WEEK,
                    page,
                    language
                )
            }
        }

        return getResult(response)
    }

    private fun getResult(response: Result<BaseResponse<Movie>>): List<Movie> {
        return when (response) {
            is Result.Success -> response.value.results
            else -> emptyList()
        }
    }
}