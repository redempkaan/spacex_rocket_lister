package com.example.myapplication.model.repository

import com.example.myapplication.model.remote.SpaceXApi
import com.example.myapplication.model.remote.NetworkResult
import com.example.myapplication.model.rocket.Rocket

class RocketRepository(private val api: SpaceXApi) {
    suspend fun getRockets(): NetworkResult<List<Rocket>> {
        return try {
            val rockets = api.getRockets()
            NetworkResult.Success(rockets)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Unknown error", e)
        }
    }
}