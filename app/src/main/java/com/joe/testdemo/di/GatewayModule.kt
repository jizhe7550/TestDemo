package com.joe.testdemo.di

import com.joe.testdemo.data.gateway.local.UserDatabaseGateway
import com.joe.testdemo.data.gateway.local.UserDatabaseGatewayImpl
import com.joe.testdemo.data.gateway.remote.UserRemoteGateway
import com.joe.testdemo.data.gateway.remote.UserRemoteGatewayImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserGatewayModule {

    @Binds
    abstract fun bindUserRemoteGateway(implementation: UserRemoteGatewayImpl): UserRemoteGateway

    @Binds
    abstract fun bindUserDatabaseGateway(implementation: UserDatabaseGatewayImpl): UserDatabaseGateway

}