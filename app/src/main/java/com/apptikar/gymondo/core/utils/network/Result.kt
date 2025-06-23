package com.apptikar.gymondo.core.utils.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerializationException

sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : com.apptikar.gymondo.core.utils.network.Error>(val error: E) :
        Result<Nothing, E>
}

inline fun <T, E : Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

fun <T, E : Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

inline fun <T, E : Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, R, E : Error> Flow<Result<T, E>>.mapSuccess(
    crossinline transform: suspend (T) -> R,
): Flow<Result<R, E>> = map { result ->
    when (result) {
        is Result.Success -> Result.Success(transform(result.data))
        is Result.Error -> result
    }
}

inline fun <T, E : Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }
}


typealias EmptyResult<E> = Result<Unit, E>

suspend inline fun <reified T> HttpClient.safeRequest(
    block: HttpClient.() -> HttpResponse,
): Result<T, Error> {
    val response = try {
        block.invoke(this)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NoInternet)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.Serialization)
    }

    return when (response.status.value) {
        in 200..299 -> {

            var errorString: Exception? = null
            val result = try {
                Result.Success(response.body<T>())
            } catch (e: Exception) {
                errorString = e
                Result.Error(error = NetworkError.ServerError)
            }
            return result
        }

        400 -> Result.Error(NetworkError.BadRequest(response.body<ResponseWrapperDto<String?>>().error?.message))
        401 -> Result.Error(NetworkError.Unauthorized)
        404 -> Result.Error(NetworkError.NotFound(response.body<ResponseWrapperDto<String?>>().error?.message))
        409 -> Result.Error(NetworkError.Conflict)
        408 -> Result.Error(NetworkError.RequestTimeout)
        413 -> Result.Error(NetworkError.PayloadTooLarge)
        422 -> Result.Error(NetworkError.Unprocessable(response.body<ResponseWrapperDto<String?>>().error?.message))
        in 500..599 -> Result.Error(NetworkError.ServerError)
        else -> Result.Error(NetworkError.Unknown)
    }
}