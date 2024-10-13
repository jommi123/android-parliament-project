package com.example.mpfinalproject.database.members

import androidx.room.Entity
import androidx.room.PrimaryKey

// 11.10.2024
// Room database table entity for storing member data

@Entity(tableName = "members")
data class MemberEntity (
    @PrimaryKey
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String
)