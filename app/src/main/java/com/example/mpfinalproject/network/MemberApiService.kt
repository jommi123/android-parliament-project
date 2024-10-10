package com.example.mpfinalproject.network


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.converter.scalars.ScalarsConverterFactory


// 7.10.2024, Jommi Koljonen, 2013099

private const val BASE_URL =
    "https://users.metropolia.fi/~peterh/"


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface MemberApiService {
    @GET("seating.json")
    suspend fun getMembers(): List<ParliamentMember>
}

object MemberApi {
    val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java)
    }
}