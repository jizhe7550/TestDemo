package com.joe.testdemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joe.testdemo.data.database.dao.TransactionDao
import com.joe.testdemo.data.database.entity.TransactionEntity


@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}