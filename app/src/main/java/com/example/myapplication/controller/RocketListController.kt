package com.example.myapplication.controller

import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.model.repository.RocketRepository
import com.example.myapplication.model.remote.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log


class RocketListController(
    private val repository: RocketRepository
) : BaseController<List<Rocket>>() {

    fun loadRockets() {
        onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = repository.getRockets()
                when (result) {
                    is NetworkResult.Success -> {
                        Log.d("RocketController", "API success: ${result.data.size} rockets")
                        onSuccess(result.data)
                    }
                    is NetworkResult.Error -> {
                        Log.e("RocketController", "API error: ${result.message}")
                        onError(result.message ?: "Unknown error")
                    }
                    else -> Log.w("RocketController", "Unknown state")
                }
            } catch (e: Exception) {
                Log.e("RocketController", "Exception: ${e.message}")
                onError("Unexpected error: ${e.message}")
            }
        }
    }

    fun retry() = loadRockets()
}