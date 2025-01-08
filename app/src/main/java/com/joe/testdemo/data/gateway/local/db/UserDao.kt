package com.joe.testdemo.data.gateway.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.joe.testdemo.data.gateway.local.entity.UserEntity

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertAll(users: List<UserEntity>)

    @Query("SELECT * FROM user_entity")
    fun getAll(): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM user_entity WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("DELETE FROM user_entity")
    suspend fun clearAll()
}