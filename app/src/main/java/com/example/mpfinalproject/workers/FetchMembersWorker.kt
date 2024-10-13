package com.example.mpfinalproject.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mpfinalproject.data.MemberDataRepository
import com.example.mpfinalproject.database.members.MemberDatabaseRepository
import com.example.mpfinalproject.database.members.MemberEntity

//12.10.2024

class FetchMembersWorker(
    context: Context,
    params: WorkerParameters,
    private val memberDataRepository: MemberDataRepository,
    private val databaseRepository: MemberDatabaseRepository
    ): CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
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

            databaseRepository.insertAllMembers(membersToSave)

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }




}