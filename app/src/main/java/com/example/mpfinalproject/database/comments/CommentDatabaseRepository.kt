package com.example.mpfinalproject.database.comments

import kotlinx.coroutines.flow.Flow

//13.10.2024, Jommi Koljonen, 2013099

interface CommentDatabaseRepository {

    fun getCommentsForMemberStream(seatNumber: Int): Flow<List<CommentEntity>>

    suspend fun insertComment(comment: CommentEntity)

    suspend fun updateComment(comment: CommentEntity)

    suspend fun deleteComment(comment: CommentEntity)
}