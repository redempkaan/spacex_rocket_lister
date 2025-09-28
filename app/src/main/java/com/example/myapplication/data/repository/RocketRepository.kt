package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.SpaceXApi

class RocketRepository(private val api: SpaceXApi) {
    suspend fun getRockets() = api.getRockets()
}