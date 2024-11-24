package com.joe.testdemo.data.model

data class TransactionDataModel(
    val id: Int,
    val transactionDate: String,
    val summary: String,
    val debit: Double,
    val credit: Double
)
