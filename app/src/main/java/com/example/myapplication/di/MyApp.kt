package com.example.myapplication.di

import android.app.Application

class MyApp : Application() {
    val appContainer: AppContainer by lazy { AppContainer() }
}