package com.joe.testdemo.data.api

import com.joe.testdemo.data.model.TransactionDataModel
import retrofit2.http.GET

const val BASE_URL = "https://60220907ae8f8700177dee68.mockapi.io/api/v1/"

interface ApiService {

    @GET("transactions")
    suspend fun getTransactionList(): List<TransactionDataModel>

}