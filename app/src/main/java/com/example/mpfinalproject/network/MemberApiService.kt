package com.example.mpfinalproject.network


import com.example.mpfinalproject.model.ParliamentMember
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// 7.10.2024, Jommi Koljonen, 2013099

//private const val BASE_URL =
//    "https://users.metropolia.fi/~peterh/"
//
//
//private val retrofit = Retrofit.Builder()
//    .baseUrl(BASE_URL)
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()


interface MemberApiService {
    @GET("seating.json")
    suspend fun getMembers(): List<ParliamentMember>
}

//object MemberApi {
//    val retrofitService: MemberApiService by lazy {
//        retrofit.create(MemberApiService::class.java)
//    }
//}