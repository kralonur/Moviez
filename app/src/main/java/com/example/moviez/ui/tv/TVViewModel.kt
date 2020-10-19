package com.example.moviez.ui.tv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getTvList() = Transformations.switchMap(queryType) {
        val list = MutableLiveData<List<TV>>()
        viewModelScope.launch {
            val result = when (it) {
                TVQueryType.TOP_RATED -> getTopRatedTVsFromRepo()
                TVQueryType.AIRING_TODAY -> getAiringTodayTVsFromRepo()
                TVQueryType.ON_THE_AIR -> getOnTheAirTVsFromRepo()
                TVQueryType.TRENDING_DAILY -> getDailyTrendingTVsFromRepo()
                TVQueryType.TRENDING_WEEKLY -> getWeeklyTrendingTVsFromRepo()
                else -> getPopularTVsFromRepo()
            }

            when (result) {
                is Result.Success -> list.postValue(result.value.results)
            }
        }

        return@switchMap list
    }

    private suspend fun getWeeklyTrendingTVsFromRepo() = trendingRepository.getTrendingTVs(
        TrendingRepository.TimeWindow.WEEK,
        1,
        null
    )

    private suspend fun getDailyTrendingTVsFromRepo() = trendingRepository.getTrendingTVs(
        TrendingRepository.TimeWindow.DAY,
        1,
        null
    )

    private suspend fun getOnTheAirTVsFromRepo() = tvRepository.getOnTheAirTVs(null, 1)

    private suspend fun getAiringTodayTVsFromRepo() = tvRepository.getAiringTodayTVs(null, 1)

    private suspend fun getPopularTVsFromRepo() = tvRepository.getPopularTVs(null, 1)

    private suspend fun getTopRatedTVsFromRepo() = tvRepository.getTopRatedTVs(null, 1)

    fun navigateCollection(navigate: (query: TVQueryType) -> Unit) {
        navigate.invoke(requireNotNull(queryType.value))
    }

    fun changeQueryType(query: TVQueryType) {
        Timber.i(query.toString())
        queryType.postValue(query)
    }

}