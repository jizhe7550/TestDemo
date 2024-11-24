package com.joe.testdemo.data.repository

import com.joe.testdemo.data.gateway.LocalRoomGateway
import com.joe.testdemo.data.gateway.RemoteApiGateway
import com.joe.testdemo.data.mapper.TransactionEntityMapper
import com.joe.testdemo.data.mapper.TransactionModelMapper
import com.joe.testdemo.di.qualifier.IoDispatcher
import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.domain.repository.TransactionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultTransactionRepository @Inject constructor(
    private val remoteApiGateway: RemoteApiGateway,
    private val localRoomGateway: LocalRoomGateway,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val transactionModelMapper: TransactionModelMapper,
    private val transactionEntityMapper: TransactionEntityMapper,
) : TransactionRepository {
    override suspend fun getTransactionList(): Flow<List<TransactionModel>> =
        withContext(ioDispatcher) {
            val transactionFlow = fetchDataFromCache()
            syncRemoteData()
            transactionFlow
        }

    private suspend fun syncRemoteData() {
        val transactionEntityList =
            remoteApiGateway.getTransactionList().map { remoteTransaction ->
                transactionEntityMapper(remoteTransaction)
            }
        localRoomGateway.insertTransactions(transactionEntityList = transactionEntityList)
    }

    private suspend fun fetchDataFromCache() = localRoomGateway.getAllTransactions()
        .map { transactionList ->
            transactionList.map { transactionEntity ->
                transactionModelMapper(transactionEntity)
            }
        }

    override suspend fun getTransactionDetail(id: Int): TransactionModel =
        withContext(ioDispatcher) {
            localRoomGateway.getTransactionById(id)?.let { transactionModelMapper(it) }
                ?: throw Exception("Transaction with ID $id not found")
        }
}