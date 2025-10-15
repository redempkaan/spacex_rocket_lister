package com.example.myapplication.model.remote

import com.example.myapplication.model.rocket.Rocket
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface SpaceXApi {
    @GET("rockets")
    suspend fun getRockets(): List<Rocket>
}

fun provideSpaceXApi(): SpaceXApi {
    return Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpaceXApi::class.java)
}