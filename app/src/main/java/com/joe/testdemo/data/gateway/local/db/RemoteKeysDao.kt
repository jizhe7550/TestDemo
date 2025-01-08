package com.joe.testdemo.data.gateway.local.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joe.testdemo.data.gateway.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Upsert
    suspend fun upsertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeysUserId(id: String): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}