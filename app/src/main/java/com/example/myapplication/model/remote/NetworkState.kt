package com.example.myapplication.model.remote

sealed class NetworkResult<out T> {

    // Success: Carries the data that is returned from a succesful API call
    data class Success<out T>(val data: T) : NetworkResult<T>()

    // Error: Carries error state and message
    data class Error(val message: String, val throwable: Throwable? = null) : NetworkResult<Nothing>()

    // Loading: Data is trying to be fetched from the API
    object Loading : NetworkResult<Nothing>()
}