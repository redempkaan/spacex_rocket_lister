package com.example.myapplication.di

import com.example.myapplication.data.remote.SpaceXApi
import com.example.myapplication.data.remote.provideSpaceXApi
import com.example.myapplication.data.repository.RocketRepository
import com.example.myapplication.data.ui.common.GlideImageLoader
import com.example.myapplication.data.ui.common.ImageLoader

class AppContainer {
    private val api: SpaceXApi = provideSpaceXApi()
    val rocketRepository = RocketRepository(api) // Repository to get rockets from API in a coroutine
    val imageLoader: ImageLoader = GlideImageLoader() // Image loader

}