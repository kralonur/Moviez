package com.example.moviez.repositories

import com.example.moviez.api.NetworkService
import com.example.moviez.model.image.ImageDetails
import com.example.moviez.model.image.PersonPostersResponse
import com.example.moviez.model.person.Person
import com.example.moviez.model.person.PersonDetails
import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.result.Result

class PersonRepository : BaseRepository() {
    private val api = NetworkService.personService

    suspend fun getPersonById(
        personId: Int,
        language: String?,
        appendToResponse: String?
    ): Result<PersonDetails> {
        return coroutineApiCall(dispatcher) {
            api.getPersonById(personId, language, appendToResponse)
        }
    }

    suspend fun getPersonTaggedImages(
        personId: Int,
        language: String?,
        page: Int?
    ): Result<BaseResponse<ImageDetails>> {
        return coroutineApiCall(dispatcher) {
            api.getPersonTaggedImages(personId, language, page)
        }
    }

    suspend fun getPersonPosters(
        personId: Int
    ): Result<PersonPostersResponse> {
        return coroutineApiCall(dispatcher) {
            api.getPersonPosters(personId)
        }
    }

    suspend fun getPopularPeoples(language: String?, page: Int?): Result<BaseResponse<Person>> {
        return coroutineApiCall(dispatcher) {
            api.getPopularPeoples(language, page)
        }
    }

    suspend fun getPersonMovieCredits(
        personId: Int,
        language: String?
    ): Result<PersonDetails.MovieCredits> {
        return coroutineApiCall(dispatcher) {
            api.getMovieCredits(personId, language)
        }
    }

    suspend fun getPersonTVCredits(
        personId: Int,
        language: String?
    ): Result<PersonDetails.TVCredits> {
        return coroutineApiCall(dispatcher) {
            api.getTVCredits(personId, language)
        }
    }
}