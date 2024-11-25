package com.joe.testdemo.data.facade

import com.joe.testdemo.data.database.dao.TransactionDao
import com.joe.testdemo.data.database.entity.TransactionEntity
import com.joe.testdemo.data.gateway.LocalRoomGateway
import com.joe.testdemo.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This layer using for potential Encrypt/decrypt
 */
class LocalRoomFacade @Inject constructor(
    private val transactionDao: TransactionDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : LocalRoomGateway {

    override suspend fun insertTransactions(transactionEntityList: List<TransactionEntity>): List<Long> =
        withContext(ioDispatcher) {
            transactionDao.insertTransactions(transactionEntityList = transactionEntityList)
        }

    override suspend fun getAllTransactions(): Flow<List<TransactionEntity>> =
        withContext(ioDispatcher) {
            transactionDao.getAllTransactions()
        }

    override suspend fun getTransactionById(id: Int): TransactionEntity? =
        withContext(ioDispatcher) {
            transactionDao.getTransactionById(id)
        }

}