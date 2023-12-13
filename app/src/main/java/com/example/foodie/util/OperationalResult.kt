package com.example.foodie.util

import com.squareup.moshi.Moshi
import retrofit2.Response

const val OFFLINE_CODE = 900

suspend fun <T, R> Response<T>.toOperationResult(onSuccess: suspend (T) -> R): OperationResult<R> {
    val body = body()
    return when {
        isSuccessful && body != null -> {
            OperationResult.Success(onSuccess(body))
        }

        !isSuccessful && code() == OFFLINE_CODE -> OperationResult.NetworkError
        else -> OperationResult.Error(errorMessage())
    }
}

private fun Response<*>.errorMessage(): ErrorResponse {
    val sharedMoshi: Moshi = Moshi.Builder().build()
    val adapter = sharedMoshi.adapter(ErrorResponse::class.java)

    val defaultResponse = ErrorResponse(999, "Whoops, something went wrong")
    val parsedErrorResponse = try {
        this.errorBody()?.string()?.let { adapter.fromJson(it) }
    } catch (e: Exception) {
        defaultResponse
    }
    return parsedErrorResponse ?: defaultResponse
}

data class ErrorResponse(
    val errorCode: Int,
    val message: String
)

sealed class OperationResult<out T> {

    class Success<T>(val result: T) : OperationResult<T>()
    class Error(val error: ErrorResponse) : OperationResult<Nothing>()
    object NetworkError : OperationResult<Nothing>()

    suspend fun onSuccess(func: suspend (T) -> Unit): OperationResult<T> {
        if (this is Success) func(result)
        return this
    }

    fun onError(func: (ErrorResponse) -> Unit): OperationResult<T> {
        if (this is Error) func(error)
        return this
    }

    fun onNetworkError(func: () -> Unit): OperationResult<T> {
        if (this is NetworkError) func()
        return this
    }
}
