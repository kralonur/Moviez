package com.example.moviez.ui.tv

import androidx.lifecycle.*
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.result.Result
import com.example.moviez.model.tv.TV
import com.example.moviez.repositories.TVRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class TVViewModel : ViewModel() {
    private val trendingRepository = TrendingRepository()
    private val tvRepository = TVRepository()

    private val queryType = MutableLiveData<TVQueryType>()

    private val _navigateCollection = MutableLiveData<TVQueryType?>()
    val navigateCollection: LiveData<TVQueryType?>
        get() = _navigateCollection

    private val _tvList: LiveData<List<TV>>
    val tvList: LiveData<List<TV>>
        get() = _tvList

    init {
        queryType.postValue(TVQueryType.POPULAR)
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

    fun changeQueryType(query: TVQueryType) {
        Timber.i(query.toString())
        queryType.postValue(query)
    }

    fun navigateToCollection() {
        _navigateCollection.postValue(queryType.value)
    }

    fun navigateToCollectionDone() {
        _navigateCollection.postValue(null)
    }
}