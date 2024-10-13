package com.example.mpfinalproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mpfinalproject.MemberDataApplication
import com.example.mpfinalproject.database.comments.CommentDatabaseRepository
import com.example.mpfinalproject.database.comments.CommentEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// 6.10.2024, Jommi Koljonen, 2013099

data class CommentUiState(
    val comment: String,
    val starRating: Int
)

data class MemberUiState(
    val starRating: Int = 0,
    val comment: String = "",
    val comments: List<CommentUiState> = emptyList()
)

// manages UI state for the MemberDetailScreen
// the viewmodel interacts with commentDatabaseRepository to:
// - load comments for specific members based on their seat number
// - add a new comment with rating for a specific member
// - handle star rating and comment input in the ui state

class MemberViewModel(
    private val commentDatabaseRepository: CommentDatabaseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MemberUiState())
    val uiState: StateFlow<MemberUiState> = _uiState.asStateFlow()


    fun updateRating(rating: Int) {
        _uiState.update { currentState ->
            currentState.copy(starRating = rating)
        }
    }

    fun loadCommentsForMember(seatNumber: Int) {
        viewModelScope.launch {
            commentDatabaseRepository.getCommentsForMemberStream(seatNumber)
                .collect { comments ->
                    val commentList =
                        comments.map { CommentUiState(it.comment, it.starRating ?: 0) }
                    _uiState.update { currentState ->
                        currentState.copy(comments = commentList)
                    }
                }
        }
    }


    fun addComment(comment: String, seatNumber: Int) {
        val commentText = _uiState.value.comment
        val starRating = _uiState.value.starRating

        if (comment.isNotBlank()) {
            val newComment = CommentEntity(
                memberSeatNumber = seatNumber,
                comment = commentText,
                starRating = starRating
            )

            viewModelScope.launch {
                commentDatabaseRepository.insertComment(newComment)

                _uiState.update { currentState ->
                    val updatedComments =
                        currentState.comments + CommentUiState(commentText, starRating)
                    currentState.copy(comments = updatedComments, comment = "")
                }
            }
        }
    }

    fun updateCommentInput(input: String) {
        _uiState.update { currentState ->
            currentState.copy(comment = input)
        }
    }

    // provides a factory to create instances of the viewmodel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MemberDataApplication)
                val commentDatabaseRepository = application.container.commentDatabaseRepository
                MemberViewModel(
                    commentDatabaseRepository = commentDatabaseRepository
                )
            }
        }
    }
}