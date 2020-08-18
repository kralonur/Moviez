package com.example.moviez.api

import com.example.moviez.api.services.*
import com.example.moviez.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

private fun getHTTPClient(): OkHttpClient {
    Timber.i("getHTTPClient called")
    return OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .build()
}

private fun getMoshi(): Moshi {
    Timber.i("getMoshi called")
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun getRetrofit(): Retrofit {
    Timber.i("getRetrofit called")
    return Retrofit.Builder()
        .client(getHTTPClient())
        .baseUrl(Constants.TMDB.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()
}

private inline fun <reified T> createService(): T =
    getRetrofit().create(T::class.java)

object NetworkService {
    val genreService by lazy { createService<GenreService>() }
    val movieService by lazy { createService<MovieService>() }
    val personService by lazy { createService<PersonService>() }
    val searchService by lazy { createService<SearchService>() }
    val trendingService by lazy { createService<TrendingService>() }
    val tvService by lazy { createService<TVService>() }
}
