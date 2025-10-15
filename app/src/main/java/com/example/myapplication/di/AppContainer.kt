package com.example.myapplication.di

import com.example.myapplication.model.remote.SpaceXApi
import com.example.myapplication.model.remote.provideSpaceXApi
import com.example.myapplication.model.repository.RocketRepository
import com.example.myapplication.model.common.GlideImageLoader
import com.example.myapplication.model.common.ImageLoader

class AppContainer {
    private val api: SpaceXApi = provideSpaceXApi()
    val rocketRepository = RocketRepository(api) // Repository to get rockets from API in a coroutine
    val imageLoader: ImageLoader = GlideImageLoader() // Image loader

}