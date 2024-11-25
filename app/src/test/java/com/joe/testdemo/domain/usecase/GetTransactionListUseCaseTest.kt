package com.joe.testdemo.domain.usecase

import app.cash.turbine.test
import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.domain.repository.TransactionRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever


class GetTransactionListUseCaseTest {

    private lateinit var underTest: GetTransactionListUseCase
    private val transactionRepository = mock<TransactionRepository>()

    @Before
    fun setUp() {
        underTest = GetTransactionListUseCase(
            transactionRepository = transactionRepository,
        )
    }

    @Test
    fun `test that usecase should return transaction list from repository`() = runTest {
        val mockTransactionList = listOf(
            TransactionModel(
                id = 1,
                transactionDate = "2021-02-02T08:11:16+13:00",
                summary = "Test summary1",
                credit = 0.0,
                debit = 197.9,
            ),
            TransactionModel(
                id = 2,
                transactionDate = "2021-02-02T08:11:16+13:00",
                summary = "Test summary2",
                credit = 0.0,
                debit = 197.9,
            ),
        )
        whenever(transactionRepository.getTransactionList()).thenReturn(flowOf(mockTransactionList))

        underTest().test {
            assertEquals(mockTransactionList, awaitItem())
            awaitComplete()
        }

        verify(transactionRepository).getTransactionList()
    }

    @Test
    fun `test that usecase should handle empty transaction list`() = runTest {
        val mockTransactionList = emptyList<TransactionModel>()
        whenever(transactionRepository.getTransactionList()).thenReturn(flowOf(mockTransactionList))

        underTest().test {
            assertEquals(mockTransactionList, awaitItem())
            awaitComplete()
        }

        verify(transactionRepository).getTransactionList()
    }


}