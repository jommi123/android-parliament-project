package com.example.mpfinalproject.data

import com.example.mpfinalproject.network.MemberApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 10.10.2024, Jommi Koljonen, 2013099

interface AppContainer {
    val memberDataRepository: MemberDataRepository
}

class DefaultAppContainer: AppContainer {

    private val baseUrl =
        "https://users.metropolia.fi/~peterh/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java)
    }

    override val memberDataRepository: MemberDataRepository by lazy {
        NetworkMemberDataRepository(retrofitService)
    }
}