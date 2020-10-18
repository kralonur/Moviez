package com.example.moviez.ui.star_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.model.person.PersonDetails
import com.example.moviez.model.result.Result
import com.example.moviez.repositories.PersonRepository
import kotlinx.coroutines.launch

class StarDetailViewModel : ViewModel() {
    private val personRepository = PersonRepository()

    private val _starDetail = MutableLiveData<PersonDetails>()
    val starDetail: LiveData<PersonDetails>
        get() = _starDetail

    fun setPerson(personId: Int) {
        viewModelScope.launch {
            val result = personRepository.getPersonById(
                personId,
                null,
                "movie_credits,tv_credits,images,tagged_images"
            )
            when (result) {
                is Result.Success -> _starDetail.postValue(result.value)
            }

        }
    }

}