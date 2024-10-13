package com.example.mpfinalproject.database.comments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Update
    suspend fun updateComment(comment: CommentEntity)

    @Delete
    suspend fun deleteComment(comment: CommentEntity)

    @Query("SELECT * FROM comments WHERE memberSeatNumber = :seatNumber")
    fun getCommentsForMember(seatNumber: Int): Flow<List<CommentEntity>>

}