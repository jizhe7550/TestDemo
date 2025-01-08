package com.joe.testdemo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.joe.testdemo.data.gateway.local.UserDatabaseGateway
import com.joe.testdemo.data.gateway.local.db.UserDatabase
import com.joe.testdemo.data.gateway.remote.UserRemoteGateway
import com.joe.testdemo.data.mapper.UserMapper
import com.joe.testdemo.di.qualifier.DefaultDispatcher
import com.joe.testdemo.domain.model.User
import com.joe.testdemo.domain.repository.UserRemoteMediator
import com.joe.testdemo.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class DefaultUserRepository @Inject constructor(
    private val userDatabase: UserDatabase,
    private val userDatabaseGateway: UserDatabaseGateway,
    private val userRemoteGateway: UserRemoteGateway,
    private val userMapper: UserMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : UserRepository {

    override fun getUsers(pageCount: Int): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(pageSize = pageCount, enablePlaceholders = false),
            remoteMediator = UserRemoteMediator(
                userDatabase = userDatabase,
                userDatabaseGateway = userDatabaseGateway,
                userRemoteGateway = userRemoteGateway,
                userMapper = userMapper,
            ),
            pagingSourceFactory = { userDatabaseGateway.getAllUsers() }
        ).flow.flowOn(defaultDispatcher).map { pagingData ->
            pagingData.map {
                userMapper(it)
            }
        }
}