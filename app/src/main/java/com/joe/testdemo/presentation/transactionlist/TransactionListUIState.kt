package com.joe.testdemo.presentation.transactionlist

import com.joe.testdemo.domain.model.TransactionModel

data class TransactionListUIState(
    val transactionList: List<TransactionModel> = emptyList()
)
