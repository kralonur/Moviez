package com.example.moviez.ui.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.model.movie.MovieDetails
import com.example.moviez.model.result.Result
import com.example.moviez.repositories.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _movieDetail = MutableLiveData<MovieDetails>()
    val movieDetail: LiveData<MovieDetails>
        get() = _movieDetail

    fun setMovie(movieId: Int) {
        viewModelScope.launch {
            when (val result =
                movieRepository.getMovieById(movieId, null, "images,credits,videos", null)) {
                is Result.Success -> _movieDetail.postValue(result.value)
            }
        }
    }

}