package com.example.myapplication.controller

import com.example.myapplication.model.remote.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow




open class BaseController<T> {  // Base of the controller that holds the UI state

    private val _uiState = MutableStateFlow<NetworkResult<T>>(NetworkResult.Loading)
    val uiState: StateFlow<NetworkResult<T>> = _uiState

    protected fun onLoading() {
        _uiState.value = NetworkResult.Loading
    }

    protected fun onSuccess(data: T) {
        _uiState.value = NetworkResult.Success(data)
    }

    protected fun onError(message: String) {
        _uiState.value = NetworkResult.Error(message)
    }
}