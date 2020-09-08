package com.example.moviez.ui.movie

import androidx.lifecycle.*
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.model.result.Result
import com.example.moviez.repositories.MovieRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel : ViewModel() {
    private val trendingRepository = TrendingRepository()
    private val movieRepository = MovieRepository()

    val queryType = MutableLiveData<MovieQueryType>()
    private val _movieList: LiveData<List<Movie>>

    val movieList: LiveData<List<Movie>>
        get() = _movieList

    init {
        queryType.postValue(MovieQueryType.POPULAR)
        _movieList = Transformations.switchMap(queryType) {
            val list = MutableLiveData<List<Movie>>()
            viewModelScope.launch {
                val result = when (it) {
                    MovieQueryType.POPULAR -> movieRepository.getPopularMovies(null, 1, null)
                    MovieQueryType.TOP_RATED -> movieRepository.getTopRatedMovies(null, 1, null)
                    MovieQueryType.UPCOMING -> movieRepository.getUpcomingMovies(null, 1, null)
                    MovieQueryType.NOW_PLAYING -> movieRepository.getNowPlayingMovies(
                        null,
                        1,
                        null
                    )
                    MovieQueryType.TRENDING_DAILY -> trendingRepository.getTrendingMovies(
                        TrendingRepository.TimeWindow.DAY,
                        1,
                        null
                    )
                    MovieQueryType.TRENDING_WEEKLY -> trendingRepository.getTrendingMovies(
                        TrendingRepository.TimeWindow.WEEK,
                        1,
                        null
                    )
                    else -> movieRepository.getPopularMovies(null, 1, null)
                }

                when (result) {
                    is Result.Success -> list.postValue(result.value.results)
                }
            }

            return@switchMap list
        }

    }

    fun changeQueryType(query: MovieQueryType) {
        Timber.i(query.toString())
        queryType.postValue(query)
    }
}