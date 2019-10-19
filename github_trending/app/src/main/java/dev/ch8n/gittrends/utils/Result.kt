package dev.ch8n.gittrends.utils

import java.lang.Exception

sealed class Result<out V, out E> {

    data class Success<out V>(val value: V) : Result<V, Nothing>()
    data class Error<out E>(val error: E) : Result<Nothing, E>()

    companion object {
        inline fun <V> build(function: () -> V): Result<V, Exception> =
            try {
                Success(function.invoke())
            } catch (e: Exception) {
                Error(e)
            }
    }
}