package com.example.mpfinalproject.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpfinalproject.network.MemberApi
import com.example.mpfinalproject.network.ParliamentMember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


// 7.10.2024, Jommi Koljonen, 2013099

data class MemberListUiState(
    val members: List<ParliamentMember> = emptyList()
)


class MemberListViewModel : ViewModel() {
//    var memberListUiState by mutableStateOf(MemberListUiState())

    private val _uiState = MutableStateFlow(MemberListUiState())
    val uiState: StateFlow<MemberListUiState> = _uiState.asStateFlow()

    init {
        getMembersData()
    }

    fun getMembersData() {
        viewModelScope.launch {
            val members = MemberApi.retrofitService.getMembers()
            Log.d("members", "${members.size} ${members[1].lastname}")
            _uiState.update { currentState ->
                currentState.copy(members = members)
            }
        }

    }
}