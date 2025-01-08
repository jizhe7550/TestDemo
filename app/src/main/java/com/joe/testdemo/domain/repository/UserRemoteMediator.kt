package com.joe.testdemo.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.joe.testdemo.data.gateway.local.UserDatabaseGateway
import com.joe.testdemo.data.gateway.local.db.UserDatabase
import com.joe.testdemo.data.gateway.local.entity.RemoteKeys
import com.joe.testdemo.data.gateway.local.entity.UserEntity
import com.joe.testdemo.data.gateway.remote.UserRemoteGateway
import com.joe.testdemo.data.mapper.UserMapper
import okio.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator constructor(
    private val userDatabase: UserDatabase,
    private val userDatabaseGateway: UserDatabaseGateway,
    private val userRemoteGateway: UserRemoteGateway,
    private val userMapper: UserMapper
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            val users = userRemoteGateway.getUsers(
                page = page,
                pageCount = state.config.pageSize
            )

            val endOfPaginationReached = users.isEmpty()

            userDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDatabaseGateway.clearAllRemoteKeys()
                    userDatabaseGateway.clearAllUsers()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = users.map {
                    RemoteKeys(id = it.login.uuid, prevKey = prevKey, nextKey = nextKey)
                }
                userDatabaseGateway.upsertRemoteKeys(keys)
                userDatabaseGateway.upsertUsers(users.map { userMapper(it) })
            }
            MediatorResult.Success(
                endOfPaginationReached = users.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { userEntity ->
                userDatabaseGateway.getRemoteKeysUserId(userEntity.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { userEntity ->
                userDatabaseGateway.getRemoteKeysUserId(userEntity.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UserEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                userDatabaseGateway.getRemoteKeysUserId(id)
            }
        }
    }


}