package com.joe.testdemo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "transactionDate")
    val transactionDate: String,
    @ColumnInfo(name = "summary")
    val summary: String,
    @ColumnInfo(name = "debit")
    val debit: Double,
    @ColumnInfo(name = "credit")
    val credit: Double
)
