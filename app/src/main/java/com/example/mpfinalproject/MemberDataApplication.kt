package com.example.mpfinalproject

import android.app.Application
import com.example.mpfinalproject.data.AppContainer
import com.example.mpfinalproject.data.DefaultAppContainer

// 10.10.2024

// AppContainer used by classes to obtain dependencies

class MemberDataApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}