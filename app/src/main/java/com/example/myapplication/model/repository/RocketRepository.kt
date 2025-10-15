package com.example.myapplication.model.repository

import com.example.myapplication.model.remote.SpaceXApi

class RocketRepository(private val api: SpaceXApi) {
    suspend fun getRockets() = api.getRockets()
}