package com.joe.testdemo.data.gateway.remote.dto

data class ApiResponse(
    val results: List<UserDto>,
    val info: Info
)

data class Info(
    val page: Int
)