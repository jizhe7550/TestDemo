package com.joe.testdemo.data.gateway.local

import androidx.paging.PagingSource
import com.joe.testdemo.data.gateway.local.entity.RemoteKeys
import com.joe.testdemo.data.gateway.local.entity.UserEntity

interface UserDatabaseGateway {

    suspend fun upsertUsers(users: List<UserEntity>)

    fun getAllUsers(): PagingSource<Int, UserEntity>

    suspend fun getUserById(id: String): UserEntity?

    suspend fun clearAllUsers()

    suspend fun upsertRemoteKeys(remoteKey: List<RemoteKeys>)

    suspend fun getRemoteKeysUserId(id: String): RemoteKeys?

    suspend fun clearAllRemoteKeys()

    suspend fun withTransaction(block: suspend () -> Unit)
}