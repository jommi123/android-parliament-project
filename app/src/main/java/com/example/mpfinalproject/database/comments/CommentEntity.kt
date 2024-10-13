package com.example.mpfinalproject.database.comments

import androidx.room.Entity
import androidx.room.PrimaryKey

// 11.10.2024, Jommi Koljonen, 2013099
// Room database table entity for storing comments

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val memberSeatNumber: Int,
    val comment: String,
    val starRating: Int? = null
)
