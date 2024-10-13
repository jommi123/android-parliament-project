package com.example.mpfinalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mpfinalproject.database.comments.CommentDao
import com.example.mpfinalproject.database.comments.CommentEntity
import com.example.mpfinalproject.database.members.MemberDao
import com.example.mpfinalproject.database.members.MemberEntity

// 11.10.2024, Jommi Koljonen, 2013099
// database class that uses singleton pattern in order to have a single instance of the database

@Database(entities = [MemberEntity::class, CommentEntity::class], version = 2, exportSchema = false)
abstract class MemberDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var Instance: MemberDatabase? = null

        fun getDatabase(context: Context): MemberDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MemberDatabase::class.java, "member_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}