package com.joe.testdemo.di

import com.joe.testdemo.data.repository.DefaultUserRepository
import com.joe.testdemo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(implementation: DefaultUserRepository): UserRepository
}