package com.example.myapplication.controller

import com.example.myapplication.model.rocket.Rocket
import com.example.myapplication.model.repository.RocketRepository
import com.example.myapplication.model.remote.NetworkResult


class RocketListController(
    private val repository: RocketRepository,
) {
    suspend fun loadRockets(): NetworkResult<List<Rocket>> {
        val result = repository.getRockets() // Getting rockets

        return when (result) { // Returning values according to the result
            is NetworkResult.Success -> NetworkResult.Success(result.data)
            is NetworkResult.Error -> NetworkResult.Error(result.message)
            is NetworkResult.Loading -> NetworkResult.Loading
        }
    }
}