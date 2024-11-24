package com.joe.testdemo.data.gateway

import com.joe.testdemo.data.model.TransactionDataModel

interface RemoteApiGateway {

    suspend fun getTransactionList(): List<TransactionDataModel>
}