package com.example.mpfinalproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mpfinalproject.MemberDataApplication
import com.example.mpfinalproject.data.MemberDataRepository
import com.example.mpfinalproject.database.members.MemberDatabaseRepository
import com.example.mpfinalproject.database.members.MemberEntity
import com.example.mpfinalproject.model.ParliamentMember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// 7.10.2024, Jommi Koljonen, 2013099

data class MemberListUiState(
    val members: List<ParliamentMember> = emptyList(),
    val selectedMember: ParliamentMember? = null
)

// manages member data for the UI state
// interacts with local memberDatabaseRepository and network memberDataRepository to:
// - insert member data into the database
// - retrieve the list of members
// - retrieve a specific member based on their seat number

class MemberListViewModel(
    private val memberDatabaseRepository: MemberDatabaseRepository,
    private val memberDataRepository: MemberDataRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(MemberListUiState())
    val uiState: StateFlow<MemberListUiState> = _uiState.asStateFlow()

    init {
        insertMembersToDatabase()
        getDatabaseMembers()
    }

    private fun insertMembersToDatabase() {
        viewModelScope.launch {
            val members = memberDataRepository.getMemberData()

            val membersToSave = members.map { member ->
                MemberEntity(
                    seatNumber = member.seatNumber,
                    lastname = member.lastname,
                    firstname = member.firstname,
                    party = member.party,
                    minister = member.minister,
                    pictureUrl = member.pictureUrl
                )
            }

            memberDatabaseRepository.insertAllMembers(membersToSave)
        }
    }

    private fun getDatabaseMembers() {
        viewModelScope.launch {
            memberDatabaseRepository.getAllMembersStream().collect { memberEntities ->
                val members = memberEntities.map { memberEntity ->
                    ParliamentMember(
                        seatNumber = memberEntity.seatNumber,
                        lastname = memberEntity.lastname,
                        firstname = memberEntity.firstname,
                        party = memberEntity.party,
                        minister = memberEntity.minister,
                        pictureUrl = memberEntity.pictureUrl
                    )
                }

                _uiState.update { currentState ->
                    currentState.copy(members = members)
                }

            }
        }
    }

    fun getMemberBySeatNumber(seatNumber: Int) {
        viewModelScope.launch {
            memberDatabaseRepository.getMemberStream(seatNumber).collect { memberEntity ->
                val parliamentMember = memberEntity?.let {
                    ParliamentMember(
                        seatNumber = it.seatNumber,
                        lastname = it.lastname,
                        firstname = it.firstname,
                        party = it.party,
                        minister = it.minister,
                        pictureUrl = it.pictureUrl
                    )
                }

                _uiState.update { currentState ->
                    currentState.copy(selectedMember = parliamentMember)
                }
            }
        }
    }


    // provides a factory to create instances of the viewmodel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MemberDataApplication)
                val databaseRepository = application.container.memberDatabaseRepository
                val memberDataRepository = application.container.memberDataRepository
                MemberListViewModel(
                    memberDatabaseRepository = databaseRepository,
                    memberDataRepository = memberDataRepository
                )
            }
        }
    }
}