package com.joe.testdemo.data.gateway

import com.joe.testdemo.data.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface LocalRoomGateway {

    suspend fun insertTransactions(transactionEntityList: List<TransactionEntity>): List<Long>

    suspend fun getAllTransactions(): Flow<List<TransactionEntity>>

    suspend fun getTransactionById(id: Int): TransactionEntity?
}