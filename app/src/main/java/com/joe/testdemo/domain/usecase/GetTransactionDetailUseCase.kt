package com.joe.testdemo.domain.usecase

import com.joe.testdemo.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionDetailUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    suspend operator fun invoke(id: Int) = transactionRepository.getTransactionDetail(id)

}