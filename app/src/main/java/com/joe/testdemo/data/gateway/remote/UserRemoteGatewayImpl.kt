package com.joe.testdemo.data.gateway.remote

import com.joe.testdemo.data.gateway.remote.api.UserApiService
import com.joe.testdemo.data.gateway.remote.dto.UserDto
import com.joe.testdemo.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteGatewayImpl @Inject constructor(
    private val apiService: UserApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserRemoteGateway {

    override suspend fun getUsers(page: Int, pageCount: Int): List<UserDto> =
        withContext(ioDispatcher) {
            apiService.getUsers(page, pageCount).results
        }

}