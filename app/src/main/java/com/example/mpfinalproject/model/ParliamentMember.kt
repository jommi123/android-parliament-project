package com.example.mpfinalproject.model

import kotlinx.serialization.Serializable

// 8.10.2024, jommi koljonen, 2013099

@Serializable
data class ParliamentMember (
    val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String
)


