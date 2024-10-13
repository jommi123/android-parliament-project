package com.example.mpfinalproject

import android.app.Application
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.mpfinalproject.data.AppContainer
import com.example.mpfinalproject.data.DefaultAppContainer
import com.example.mpfinalproject.workers.FetchMembersWorker
import java.util.concurrent.TimeUnit

// 10.10.2024

// AppContainer used by classes to obtain dependencies

class MemberDataApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)

        //schedulePeriodicFetchMembersWork(this)
    }
}

fun schedulePeriodicFetchMembersWork(context: Context) {
    val oneTimeWorkRequest = OneTimeWorkRequestBuilder<FetchMembersWorker>().build()
    WorkManager.getInstance(context).enqueue(oneTimeWorkRequest)
}