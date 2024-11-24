package com.joe.testdemo.di

import com.joe.testdemo.data.repository.DefaultTransactionRepository
import com.joe.testdemo.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTransactionRepository(implementation: DefaultTransactionRepository): TransactionRepository

}