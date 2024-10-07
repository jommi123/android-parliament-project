package com.example.mpfinalproject.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// 6.10.2024, Jommi Koljonen, 2013099

class MemberViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MemberUiState())
    val uiState: StateFlow<MemberUiState> = _uiState.asStateFlow()



    fun updateRating(rating: Int) {
        _uiState.update { currentState ->
            currentState.copy(starRating = rating)
        }
    }

    fun addComment(comment: String) {
        if (comment.isNotBlank()) {
            _uiState.update { currentState ->
                val updatedComments = currentState.comments + comment
                currentState.copy(comments = updatedComments, comment = "")
            }
        }
    }

    fun updateCommentInput(input: String) {
        _uiState.update { currentState ->
            currentState.copy(comment = input)
        }
    }

}