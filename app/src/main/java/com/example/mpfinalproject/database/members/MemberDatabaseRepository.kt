package com.example.mpfinalproject.database.members

import kotlinx.coroutines.flow.Flow

// 11.10.2024

interface MemberDatabaseRepository {

    fun getAllMembersStream(): Flow<List<MemberEntity>>

    fun getMemberStream(seatNumber: Int): Flow<MemberEntity?>

    suspend fun insertMember(memberEntity: MemberEntity)

    suspend fun insertAllMembers(members: List<MemberEntity>)

    suspend fun deleteMember(memberEntity: MemberEntity)

    suspend fun updateMember(memberEntity: MemberEntity)

}