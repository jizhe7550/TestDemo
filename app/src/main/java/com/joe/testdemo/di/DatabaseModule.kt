package com.joe.testdemo.di

import android.content.Context
import com.joe.testdemo.data.gateway.local.db.RemoteKeysDao
import com.joe.testdemo.data.gateway.local.db.UserDao
import com.joe.testdemo.data.gateway.local.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.init(
            context = context,
        )
    }

    @Provides
    @Singleton
    fun providerUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun providerRemoteKeysDao(database: UserDatabase): RemoteKeysDao {
        return database.remoteKeysDao()
    }
}