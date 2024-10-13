package com.example.mpfinalproject.network


import com.example.mpfinalproject.model.ParliamentMember
import retrofit2.http.GET


// 7.10.2024, Jommi Koljonen, 2013099

interface MemberApiService {
    @GET("seating.json")
    suspend fun getMembers(): List<ParliamentMember>
}
