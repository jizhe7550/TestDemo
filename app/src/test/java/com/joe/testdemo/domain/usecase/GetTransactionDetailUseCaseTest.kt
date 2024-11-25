package com.joe.testdemo.domain.usecase

import com.joe.testdemo.domain.model.TransactionModel
import com.joe.testdemo.domain.repository.TransactionRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetTransactionDetailUseCaseTest {

    private lateinit var underTest: GetTransactionDetailUseCase
    private val transactionRepository = mock<TransactionRepository>()


    @Before
    fun setUp() {
        underTest = GetTransactionDetailUseCase(
            transactionRepository = transactionRepository,
        )
    }

    @Test
    fun `test that usecase should fetch transaction detail from repository`() = runTest {
        val transactionId = 1
        val mockTransaction = TransactionModel(
            id = transactionId,
            transactionDate = "2021-02-02T08:11:16+13:00",
            summary = "Test summary",
            credit = 0.0,
            debit = 197.9,
        )

        whenever(transactionRepository.getTransactionDetail(transactionId)).thenReturn(
            mockTransaction
        )

        val result = underTest.invoke(transactionId)

        verify(transactionRepository).getTransactionDetail(transactionId)

        assertEquals(mockTransaction, result)
    }

    @Test(expected = Exception::class)
    fun `test that usecase should throw exception when repository fails`() = runTest {
        val transactionId = 1

        whenever(transactionRepository.getTransactionDetail(transactionId)).thenThrow(Exception("Error"))

        underTest.invoke(transactionId)
    }

}