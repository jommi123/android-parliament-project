package com.example.mpfinalproject.ui

import com.example.mpfinalproject.model.ParliamentMember

// 6.10.2024, Jommi Koljonen, 2013099

data class MemberUiState(
    val selectedMember: ParliamentMember? = null,
    val starRating: Int = 0,
    val comment: String = "",
    val comments: List<String> = emptyList()
)