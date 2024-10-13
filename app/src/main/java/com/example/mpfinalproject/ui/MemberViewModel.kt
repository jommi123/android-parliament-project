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
import com.example.mpfinalproject.database.comments.OfflineCommentDatabaseRepository
import com.example.mpfinalproject.model.ParliamentMember
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
    val comments: List<String> = emptyList()
)


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
                    val commentList = comments.map { it.comment }
                    _uiState.update { currentState ->
                        currentState.copy(comments = commentList)
                    }
                }
        }
    }


    fun addComment(comment: String, seatNumber: Int) {
        val commentText = _uiState.value.comment
        if (comment.isNotBlank()) {
            val newComment = CommentEntity(
                memberSeatNumber = seatNumber,
                comment = commentText,
                starRating = _uiState.value.starRating
            )

            viewModelScope.launch {
                commentDatabaseRepository.insertComment(newComment)

                _uiState.update { currentState ->
                    val updatedComments = currentState.comments + commentText
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