package com.example.moviez.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.model.result.Result
import com.example.moviez.repositories.MovieRepository
import com.example.moviez.repositories.TrendingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

private val DEFAULT_QUERY_TYPE = MovieQueryType.POPULAR

class MovieViewModel : ViewModel() {
    private val trendingRepository = TrendingRepository()
    private val movieRepository = MovieRepository()

    private val queryType = MutableLiveData(DEFAULT_QUERY_TYPE)

    fun getMovieList() = Transformations.switchMap(queryType) {
        val list = MutableLiveData<List<Movie>>()
        viewModelScope.launch {
            val result = when (it) {
                MovieQueryType.TOP_RATED -> getTopRatedMoviesFromRepo()
                MovieQueryType.UPCOMING -> getUpcomingMoviesFromRepo()
                MovieQueryType.NOW_PLAYING -> getNowPlayingMoviesFromRepo()
                MovieQueryType.TRENDING_DAILY -> getDailyTrendingMoviesFromRepo()
                MovieQueryType.TRENDING_WEEKLY -> getWeeklyTrendingMoviesFromRepo()
                else -> getPopularMoviesFromRepo()
            }

            when (result) {
                is Result.Success -> list.postValue(result.value.results)
            }
        }

        list
    }


    private suspend fun getPopularMoviesFromRepo() =
        movieRepository.getPopularMovies(null, 1, null)

    private suspend fun getTopRatedMoviesFromRepo() =
        movieRepository.getTopRatedMovies(null, 1, null)

    private suspend fun getUpcomingMoviesFromRepo() =
        movieRepository.getUpcomingMovies(null, 1, null)

    private suspend fun getNowPlayingMoviesFromRepo() =
        movieRepository.getNowPlayingMovies(null, 1, null)

    private suspend fun getDailyTrendingMoviesFromRepo() =
        trendingRepository.getTrendingMovies(TrendingRepository.TimeWindow.DAY, 1, null)

    private suspend fun getWeeklyTrendingMoviesFromRepo() =
        trendingRepository.getTrendingMovies(TrendingRepository.TimeWindow.WEEK, 1, null)


    fun changeQueryType(query: MovieQueryType) {
        Timber.i(query.toString())
        queryType.postValue(query)
    }

    fun navigateCollection(navigate: (query: MovieQueryType) -> Unit) {
        navigate.invoke(requireNotNull(queryType.value))
    }

}