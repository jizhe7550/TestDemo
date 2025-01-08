package com.joe.testdemo.data.gateway.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joe.testdemo.data.gateway.local.entity.RemoteKeys
import com.joe.testdemo.data.gateway.local.entity.UserEntity

const val USER_DATABASE_NAME = "user_database"

@Database(
    entities = [
        UserEntity::class,
        RemoteKeys::class
    ],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        fun init(
            context: Context,
        ): UserDatabase = Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            USER_DATABASE_NAME
        ).build()
    }
}