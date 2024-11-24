package com.joe.testdemo.data.mapper

import com.joe.testdemo.data.database.entity.TransactionEntity
import com.joe.testdemo.data.model.TransactionDataModel
import javax.inject.Inject

class TransactionEntityMapper @Inject constructor() {

    operator fun invoke(transactionDataModel: TransactionDataModel) = TransactionEntity(
        id = transactionDataModel.id,
        transactionDate = transactionDataModel.transactionDate,
        summary = transactionDataModel.summary,
        debit = transactionDataModel.debit,
        credit = transactionDataModel.credit,
    )

}