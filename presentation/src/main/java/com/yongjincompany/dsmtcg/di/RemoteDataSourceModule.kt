package com.yongjincompany.dsmtcg.di

import com.yongjincompany.data.remote.datasource.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    /*@Singleton
    @Binds
    abstract fun provideRemoteSocketDataSource(
        remoteSocketDataSourceImpl: RemoteSocketDataSourceImpl,
    ): RemoteSocketDataSource*/

    @Binds
    abstract fun provideRemoteUserDataSource(
        remoteUserDataSourceImpl: RemoteUserDataSourceImpl
    ): RemoteUserDataSource

    @Binds
    abstract fun provideRemoteImageDataSource(
        remoteImagesDataSourceImpl: RemoteImagesDataSourceImpl
    ): RemoteImagesDataSource

    @Binds
    abstract fun provideRemoteCardDataSource(
        remoteCardDataSourceImpl: RemoteCardDataSourceImpl
    ): RemoteCardDataSource

    @Binds
    abstract fun provideRemoteChestDataSource(
        remoteChestDataSourceImpl: RemoteChestDataSourceImpl
    ): RemoteChestDataSource

    @Binds
    abstract fun provideRemoteRankDataSource(
        remoteRankDataSourceImpl: RemoteRankDataSourceImpl
    ): RemoteRankDataSource

}