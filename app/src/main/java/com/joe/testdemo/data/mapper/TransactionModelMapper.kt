package com.joe.testdemo.data.mapper

import com.joe.testdemo.data.database.entity.TransactionEntity
import com.joe.testdemo.domain.model.TransactionModel
import javax.inject.Inject

class TransactionModelMapper @Inject constructor() {

    operator fun invoke(transactionEntity: TransactionEntity) = TransactionModel(
        id = transactionEntity.id,
        transactionDate = transactionEntity.transactionDate,
        summary = transactionEntity.summary,
        debit = transactionEntity.debit,
        credit = transactionEntity.credit,
    )

}