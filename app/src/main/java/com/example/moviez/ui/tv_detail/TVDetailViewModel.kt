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

    private val _tvDetail = MutableLiveData<TVDetails>()
    val tvDetail: LiveData<TVDetails>
        get() = _tvDetail

    fun setTv(tvId: Int) {
        viewModelScope.launch {
            when (val result =
                tvRepository.getTVById(tvId, null, "images,credits,videos", null)) {
                is Result.Success -> _tvDetail.postValue(result.value)
            }
        }
    }

}