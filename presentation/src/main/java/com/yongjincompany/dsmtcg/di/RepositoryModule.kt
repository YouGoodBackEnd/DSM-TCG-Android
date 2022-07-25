package com.yongjincompany.dsmtcg.di

import com.yongjincompany.data.repository.*
import com.yongjincompany.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /*@Singleton
    @Binds
    abstract fun providesSocketRepository(
        socketRepositoryImpl: SocketRepositoryImpl
    ): SocketRepository*/

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

}