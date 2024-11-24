package com.joe.testdemo.domain.model

data class TransactionModel(
    val id: Int,
    val transactionDate: String,
    val summary: String,
    val debit: Double,
    val credit: Double
)
