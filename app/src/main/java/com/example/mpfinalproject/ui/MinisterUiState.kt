package com.example.mpfinalproject.ui

import com.example.mpfinalproject.data.ParliamentMember

// 6.10.2024, Jommi Koljonen, 2013099

data class MinisterUiState(
    val selectedMember: ParliamentMember? = null,
    val starRating: Int? = null,
    val comment: String? = null
)