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

    fun getMovieDetail(movieId: Int): LiveData<MovieDetails> {
        val movieDetails = MutableLiveData<MovieDetails>()

        viewModelScope.launch {
            when (val result = getMovieDetailFromRepo(movieId)) {
                is Result.Success -> movieDetails.postValue(result.value)
            }
        }

        return movieDetails
    }

    private suspend fun getMovieDetailFromRepo(movieId: Int) =
        movieRepository.getMovieById(movieId, null, "images,credits,videos", null)

}