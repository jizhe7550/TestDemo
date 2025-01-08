package com.joe.testdemo.data.gateway.remote

import com.joe.testdemo.data.gateway.remote.dto.UserDto

interface UserRemoteGateway {

    suspend fun getUsers(
        page: Int,
        pageCount: Int,
    ): List<UserDto>

}
