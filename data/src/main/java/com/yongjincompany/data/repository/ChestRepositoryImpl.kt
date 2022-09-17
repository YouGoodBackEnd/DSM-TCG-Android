package com.yongjincompany.data.repository

import com.yongjincompany.data.remote.datasource.RemoteChestDataSource
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
import com.yongjincompany.domain.repository.ChestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChestRepositoryImpl @Inject constructor(
    private val remoteChestDataSource: RemoteChestDataSource
): ChestRepository {
    override suspend fun fetchFreeChestTime(): Flow<FetchFreeChestTimeEntity> =
        OfflineCacheUtil<FetchFreeChestTimeEntity>()
            .remoteData { remoteChestDataSource.fetchFreeChestTime() }
            .createRemoteFlow()

    override suspend fun fetchSpecialChestTime(): Flow<FetchSpecialChestTimeEntity> =
        OfflineCacheUtil<FetchSpecialChestTimeEntity>()
            .remoteData { remoteChestDataSource.fetchSpecialChestTime() }
            .createRemoteFlow()
}