package com.example.mpfinalproject.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mpfinalproject.MemberDataApplication
import com.example.mpfinalproject.data.MemberDataRepository
import com.example.mpfinalproject.data.NetworkMemberDataRepository
import com.example.mpfinalproject.model.ParliamentMember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// 7.10.2024, Jommi Koljonen, 2013099

data class MemberListUiState(
    val members: List<ParliamentMember> = emptyList()
)


class MemberListViewModel(private val memberDataRepository: MemberDataRepository) : ViewModel() {
//    var memberListUiState by mutableStateOf(MemberListUiState())

    private val _uiState = MutableStateFlow(MemberListUiState())
    val uiState: StateFlow<MemberListUiState> = _uiState.asStateFlow()

    init {
        getMembersData()
    }

    fun getMembersData() {
        viewModelScope.launch {
            //val members = MemberApi.retrofitService.getMembers()
//            val memberDataRepository = NetworkMemberDataRepository()
            val members = memberDataRepository.getMemberData()

            _uiState.update { currentState ->
                currentState.copy(members = members)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MemberDataApplication)
                val memberDataRepository = application.container.memberDataRepository
                MemberListViewModel(memberDataRepository = memberDataRepository)
            }
        }
    }
}