package com.example.mpfinalproject.database.comments

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val memberSeatNumber: Int,
    val comment: String,
    val starRating: Int? = null
)
