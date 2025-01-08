package com.joe.testdemo.data.gateway.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.joe.testdemo.data.gateway.local.db.RemoteKeysDao
import com.joe.testdemo.data.gateway.local.db.UserDao
import com.joe.testdemo.data.gateway.local.db.UserDatabase
import com.joe.testdemo.data.gateway.local.entity.RemoteKeys
import com.joe.testdemo.data.gateway.local.entity.UserEntity
import javax.inject.Inject

class UserDatabaseGatewayImpl @Inject constructor(
    private val userDao: UserDao,
    private val remoteKeysDao: RemoteKeysDao,
    private val roomDatabase: UserDatabase
) : UserDatabaseGateway {

    override suspend fun upsertUsers(users: List<UserEntity>) = userDao.upsertAll(users)

    override fun getAllUsers(): PagingSource<Int, UserEntity> = userDao.getAll()

    override suspend fun getUserById(id: String): UserEntity? = userDao.getUserById(id = id)

    override suspend fun clearAllUsers() = userDao.clearAll()

    override suspend fun upsertRemoteKeys(remoteKey: List<RemoteKeys>) =
        remoteKeysDao.upsertAll(remoteKey)

    override suspend fun getRemoteKeysUserId(id: String): RemoteKeys? =
        remoteKeysDao.getRemoteKeysUserId(id = id)

    override suspend fun clearAllRemoteKeys() = remoteKeysDao.clearAll()

    override suspend fun withTransaction(block: suspend () -> Unit) {
        roomDatabase.withTransaction { block() }
    }
}