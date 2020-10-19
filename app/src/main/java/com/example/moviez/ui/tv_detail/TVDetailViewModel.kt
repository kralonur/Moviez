package com.example.moviez.ui.tv_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.model.result.Result
import com.example.moviez.model.tv.TVDetails
import com.example.moviez.repositories.TVRepository
import kotlinx.coroutines.launch

class TVDetailViewModel : ViewModel() {
    private val tvRepository = TVRepository()

    fun getTvDetail(tvId: Int): LiveData<TVDetails> {
        val tvDetails = MutableLiveData<TVDetails>()

        viewModelScope.launch {
            when (val result = getTvDetailFromRepo(tvId)) {
                is Result.Success -> tvDetails.postValue(result.value)
            }
        }

        return tvDetails
    }

    private suspend fun getTvDetailFromRepo(tvId: Int) =
        tvRepository.getTVById(tvId, null, "images,credits,videos", null)

}