package com.example.mpfinalproject.data

import com.example.mpfinalproject.model.ParliamentMember
import com.example.mpfinalproject.network.MemberApiService

// 10.10.2024, Jommi Koljonen, 2013099


interface MemberDataRepository {
    suspend fun getMemberData(): List<ParliamentMember>

}

// fetches member data using memberApiService
class NetworkMemberDataRepository(
    private val memberApiService: MemberApiService
): MemberDataRepository {
    override suspend fun getMemberData(): List<ParliamentMember> {
        return memberApiService.getMembers()
    }
}