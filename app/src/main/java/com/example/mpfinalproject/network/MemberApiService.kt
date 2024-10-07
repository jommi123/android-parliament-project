package com.example.mpfinalproject.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

// 7.10.2024, Jommi Koljonen, 2013099

private const val BASE_URL =
    "https://users.metropolia.fi/~peterh/seating.json"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface MemberApiService {
    @GET
    fun getMembers(): String
}

object MemberApi {
    val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java)
    }
}