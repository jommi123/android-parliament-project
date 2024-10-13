package com.example.mpfinalproject.data

import android.content.Context
import com.example.mpfinalproject.database.members.MemberDatabaseRepository
import com.example.mpfinalproject.database.MemberDatabase
import com.example.mpfinalproject.database.comments.CommentDatabaseRepository
import com.example.mpfinalproject.database.comments.OfflineCommentDatabaseRepository
import com.example.mpfinalproject.database.members.OfflineMemberDatabaseRepository
import com.example.mpfinalproject.network.MemberApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 10.10.2024, Jommi Koljonen, 2013099


interface AppContainer {
    val memberDataRepository: MemberDataRepository
    val memberDatabaseRepository: MemberDatabaseRepository
    val commentDatabaseRepository: CommentDatabaseRepository
}
// provides instances of repositories that manage data
class DefaultAppContainer(private val context: Context) : AppContainer {

    private val baseUrl =
        "https://users.metropolia.fi/~peterh/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: MemberApiService by lazy {
        retrofit.create(MemberApiService::class.java)
    }

    override val memberDataRepository: MemberDataRepository by lazy {
        NetworkMemberDataRepository(retrofitService)
    }

    override val memberDatabaseRepository: MemberDatabaseRepository by lazy {
        OfflineMemberDatabaseRepository(MemberDatabase.getDatabase(context).memberDao())
    }

    override val commentDatabaseRepository: CommentDatabaseRepository by lazy {
        OfflineCommentDatabaseRepository(MemberDatabase.getDatabase(context).commentDao())
    }
}