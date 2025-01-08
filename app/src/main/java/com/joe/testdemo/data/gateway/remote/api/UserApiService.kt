package com.joe.testdemo.data.gateway.remote.api

import com.joe.testdemo.data.gateway.remote.dto.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") pageCount: Int,
    ): ApiResponse

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }
}