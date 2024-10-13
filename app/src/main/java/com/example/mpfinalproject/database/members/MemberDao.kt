package com.example.mpfinalproject.database.members

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// 11.10.2024, Jommi Koljonen, 2013099

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: MemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMembers(members: List<MemberEntity>)

    @Update
    suspend fun update(member: MemberEntity)

    @Delete
    suspend fun delete(member: MemberEntity)

    @Query("SELECT * from members WHERE seatNumber = :seatNumber")
    fun getMember(seatNumber: Int): Flow<MemberEntity>

    @Query("SELECT * from members")
    fun getAllMembers(): Flow<List<MemberEntity>>

}