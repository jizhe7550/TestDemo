package com.joe.testdemo.di

import com.joe.testdemo.data.facade.LocalRoomFacade
import com.joe.testdemo.data.facade.RemoteApiFacade
import com.joe.testdemo.data.gateway.LocalRoomGateway
import com.joe.testdemo.data.gateway.RemoteApiGateway
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {

    @Binds
    @Singleton
    abstract fun bindRemoteApiGateway(implementation: RemoteApiFacade): RemoteApiGateway

    @Binds
    @Singleton
    abstract fun bindLocalRoomGateway(implementation: LocalRoomFacade): LocalRoomGateway
}