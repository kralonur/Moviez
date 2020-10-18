package com.example.moviez.ui.tv

import androidx.lifecycle.*
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.result.Result
import com.example.moviez.model.tv.TV
import com.example.moviez.repositories.TVRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

private val DEFAULT_QUERY_TYPE = TVQueryType.POPULAR


class TVViewModel : ViewModel() {
    private val trendingRepository = TrendingRepository()
    private val tvRepository = TVRepository()

    private val queryType = MutableLiveData<TVQueryType>(DEFAULT_QUERY_TYPE)

    private val _tvList: LiveData<List<TV>>
    val tvList: LiveData<List<TV>>
        get() = _tvList

    init {
        _tvList = Transformations.switchMap(queryType) {
            val list = MutableLiveData<List<TV>>()
            viewModelScope.launch {
                val result = when (it) {
                    TVQueryType.POPULAR -> tvRepository.getPopularTVs(null, 1)
                    TVQueryType.TOP_RATED -> tvRepository.getTopRatedTVs(null, 1)
                    TVQueryType.AIRING_TODAY -> tvRepository.getAiringTodayTVs(null, 1)
                    TVQueryType.ON_THE_AIR -> tvRepository.getOnTheAirTVs(null, 1)
                    TVQueryType.TRENDING_DAILY -> trendingRepository.getTrendingTVs(
                        TrendingRepository.TimeWindow.DAY,
                        1,
                        null
                    )
                    TVQueryType.TRENDING_WEEKLY -> trendingRepository.getTrendingTVs(
                        TrendingRepository.TimeWindow.WEEK,
                        1,
                        null
                    )
                    else -> tvRepository.getPopularTVs(null, 1)
                }

                when (result) {
                    is Result.Success -> list.postValue(result.value.results)
                }
            }

            return@switchMap list
        }

    }

    fun navigateCollection(navigate: (query: TVQueryType) -> Unit) {
        navigate.invoke(requireNotNull(queryType.value))
    }

    fun changeQueryType(query: TVQueryType) {
        Timber.i(query.toString())
        queryType.postValue(query)
    }

}