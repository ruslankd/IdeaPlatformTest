package ru.kabirov.data.model

sealed class RequestResult<out T : Any> {
    class InProgress<T : Any> : RequestResult<T>()
    class Error<T : Any>(val error: Throwable) : RequestResult<T>()
    class Success<T : Any>(val data: T) : RequestResult<T>()
}