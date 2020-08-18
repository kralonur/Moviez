package com.example.moviez.api.services

import com.example.moviez.model.result.BaseResponse
import com.example.moviez.model.image.ImageDetails
import com.example.moviez.model.image.PersonPostersResponse
import com.example.moviez.model.person.Person
import com.example.moviez.model.person.PersonDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {
    @GET("person/{person_id}")
    suspend fun getPersonById(
        @Path("person_id") personId: Int,
        @Query("language") language: String?,
        @Query("append_to_response") appendToResponse: String?
    ): PersonDetails

    @GET("person/{person_id}/tagged_images")
    suspend fun getPersonTaggedImages(
        @Path("person_id") personId: Int,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): BaseResponse<ImageDetails>

    @GET("person/{person_id}/images")
    suspend fun getPersonPosters(
        @Path("person_id") personId: Int
    ): PersonPostersResponse

    @GET("person/popular")
    suspend fun getPopularPeoples(
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): BaseResponse<Person>

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieCredits(
        @Path("person_id") personId: Int,
        @Query("language") language: String?
    ): PersonDetails.MovieCredits

    @GET("person/{person_id}/tv_credits")
    suspend fun getTVCredits(
        @Path("person_id") personId: Int,
        @Query("language") language: String?
    ): PersonDetails.TVCredits
}