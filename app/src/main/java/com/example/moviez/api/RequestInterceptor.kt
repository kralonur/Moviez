package com.example.moviez.api

import com.example.moviez.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.TMDB_API_KEY}"
            )
            .build()

        return chain.proceed(request)
    }
}