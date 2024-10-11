package com.example.mpfinalproject.database

import kotlinx.coroutines.flow.Flow

// 11.10.2024

// repository that provides insert, update, delete and retrieve of MemberEntity

interface DatabaseRepository {

    fun getAllMembersStream(): Flow<List<MemberEntity>>

    fun getMemberStream(seatNumber: Int): Flow<MemberEntity?>

    suspend fun insertMember(memberEntity: MemberEntity)

    suspend fun insertAllMembers(members: List<MemberEntity>)

    suspend fun deleteMember(memberEntity: MemberEntity)

    suspend fun updateMember(memberEntity: MemberEntity)

}