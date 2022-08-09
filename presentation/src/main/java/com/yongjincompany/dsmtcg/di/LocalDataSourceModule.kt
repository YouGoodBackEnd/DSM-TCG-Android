package com.yongjincompany.dsmtcg.di


import com.yongjincompany.data.local.datasource.LocalCardDataSource
import com.yongjincompany.data.local.datasource.LocalCardDataSourceImpl
import com.yongjincompany.data.local.datasource.LocalUserDataSource
import com.yongjincompany.data.local.datasource.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun provideLocalUserDataSource(
        localUserDataSourceImpl: LocalUserDataSourceImpl
    ): LocalUserDataSource

    @Binds
    abstract fun provideLocalCardDataSource(
        localCardDataSourceImpl: LocalCardDataSourceImpl
    ): LocalCardDataSource
}