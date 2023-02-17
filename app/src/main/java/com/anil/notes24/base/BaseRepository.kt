package com.anil.notes24.base

import com.anil.notes24.network.Result
import com.anil.notes24.network.CustomException
import retrofit2.Response


open class BaseRepository {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it)
                }
            }
            return error(response.code(), response)
        } catch (e: Exception) {
            // TODO crash is here when network call is in progress and back button is pressed
            //Timber.e("Exception $e")
            return error(e.message ?: e.toString())
            // return error(statusCode = response.code(), message = e.message ?: e.toString())
        }
    }

    /**
     * Network error handling
     * When status code is 400 - show the error message received from server
     * */
    private fun <T> error(statusCode: Int? = 0, response: Response<T>): Result<T> {
        // handle 400 response code
        var message: String = "Network error!"
        var errorFlag: String? = null


        return Result.Error(
            CustomException(
                statusCode,
                message ?: "",
                errorFlag
            )
        )
    }

    /**
     * Error thrown when there is no internet
     * */
    private fun <T> error(message: String): Result<T> {
        return Result.Error(
            CustomException(
                -100,
                message
            )
        )
    }
}