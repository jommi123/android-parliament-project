package com.example.mpfinalproject.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// 6.10.2024, Jommi Koljonen, 2013099

class MinisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MinisterUiState())
    val uiState: StateFlow<MinisterUiState> = _uiState.asStateFlow()



    fun updateStarRating(rating: Int) {
        _uiState.value = _uiState.value.copy(starRating = rating)
    }

    fun addComment(comment: String) {

    }

}