package com.joe.testdemo.domain.repository

import com.joe.testdemo.domain.model.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun getTransactionList(): Flow<List<TransactionModel>>

    suspend fun getTransactionDetail(id:Int): TransactionModel
}