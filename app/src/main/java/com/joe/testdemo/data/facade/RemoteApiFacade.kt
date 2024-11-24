package com.joe.testdemo.data.facade

import com.joe.testdemo.data.api.ApiService
import com.joe.testdemo.data.gateway.RemoteApiGateway
import com.joe.testdemo.data.model.TransactionDataModel
import com.joe.testdemo.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteApiFacade @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : RemoteApiGateway {
    override suspend fun getTransactionList(): List<TransactionDataModel> = withContext(ioDispatcher) {
        apiService.getTransactionList()
    }
}