package com.joe.testdemo.presentation.transactiondetail

import com.joe.testdemo.domain.model.TransactionModel

data class TransactionDetailUIState(
    val transaction: TransactionModel? = null
)
