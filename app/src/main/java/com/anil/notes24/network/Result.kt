package com.anil.notes24.network

import androidx.lifecycle.MutableLiveData

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val responseData: T) : Result<T>()
    data class Error(val exception: CustomException?) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$responseData]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && this.responseData != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.responseData ?: fallback
}

val <T> Result<T>.responseData: T?
    get() = (this as? Result.Success)?.responseData

val <T> Result<T>.error: Result.Error?
    get() = (this as? Result.Error)

/**
 * Updates value of [liveData] if [Result] is of type [Success]
 */
inline fun <reified T> Result<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is Result.Success) {
        liveData.value = responseData
    }
}
