package com.joe.testdemo.domain.repository

import androidx.paging.PagingData
import com.joe.testdemo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(pageCount: Int): Flow<PagingData<User>>
}