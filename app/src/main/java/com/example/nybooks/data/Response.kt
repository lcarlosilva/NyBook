package com.example.nybooks.data

sealed class Response<out T> {
    data class Success<T>(val data : T ) : Response<T>()
    data class Error(val throwable: Throwable) : Response<Nothing>()
}