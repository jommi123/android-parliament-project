package com.example.mpfinalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 11.10.2024
// Database class with a singleton Instance object

@Database(entities = [MemberEntity::class], version = 1, exportSchema = false)
abstract class MemberDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao

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