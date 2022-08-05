package com.yongjincompany.dsmtcg.di

import com.yongjincompany.data.remote.datasource.RemoteImagesDataSource
import com.yongjincompany.data.remote.datasource.RemoteImagesDataSourceImpl
import com.yongjincompany.data.remote.datasource.RemoteUserDataSource
import com.yongjincompany.data.remote.datasource.RemoteUserDataSourceImpl
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

}