package com.example.myapplication.services

import com.example.myapplication.models.Rocket
import retrofit2.http.GET

interface SpaceXApi {
    @GET("rockets")
    suspend fun getRockets(): List<Rocket>
}