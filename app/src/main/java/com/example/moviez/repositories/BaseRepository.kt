package com.example.moviez.repositories

import com.example.moviez.model.error.ErrorResponse
import com.example.moviez.model.result.Result
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

open class BaseRepository {
    suspend fun <T> coroutineApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Result.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = throwableToErrorResponse(throwable)

                        Timber.e("HttpException: $code, ${errorResponse?.statusCode}, ${errorResponse?.statusMessage}")

                        Result.Error(code, errorResponse)
                    }
                    else -> {
                        Timber.e(throwable)

                        Result.Error(null, null)
                    }
                }
            }
        }
    }

    open val dispatcher = Dispatchers.IO


    private fun throwableToErrorResponse(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}