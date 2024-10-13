package com.example.mpfinalproject.database.members

import kotlinx.coroutines.flow.Flow

// 11.10.2024
// manages member database operations with MemberDao

class OfflineMemberDatabaseRepository(private val memberDao: MemberDao) : MemberDatabaseRepository {
    override fun getAllMembersStream(): Flow<List<MemberEntity>> =
        memberDao.getAllMembers()


    override fun getMemberStream(seatNumber: Int): Flow<MemberEntity?> =
        memberDao.getMember(seatNumber)


    override suspend fun insertMember(memberEntity: MemberEntity) =
        memberDao.insert(memberEntity)

    override suspend fun insertAllMembers(members: List<MemberEntity>) {
        memberDao.insertAllMembers(members)
    }

    override suspend fun deleteMember(memberEntity: MemberEntity) =
        memberDao.delete(memberEntity)


    override suspend fun updateMember(memberEntity: MemberEntity) =
        memberDao.update(memberEntity)


}