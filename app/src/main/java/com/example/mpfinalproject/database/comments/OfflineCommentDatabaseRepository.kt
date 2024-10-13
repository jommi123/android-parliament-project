package com.example.mpfinalproject.database.comments

import kotlinx.coroutines.flow.Flow

// 13.10.2024

class OfflineCommentDatabaseRepository(private val commentDao: CommentDao): CommentDatabaseRepository {
    override fun getCommentsForMemberStream(seatNumber: Int): Flow<List<CommentEntity>> =
        commentDao.getCommentsForMember(seatNumber)


    override suspend fun insertComment(comment: CommentEntity) =
        commentDao.insertComment(comment)


    override suspend fun updateComment(comment: CommentEntity) {
        commentDao.updateComment(comment)
    }

    override suspend fun deleteComment(comment: CommentEntity) {
        commentDao.deleteComment(comment)
    }
}