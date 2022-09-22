package com.yongjincompany.data.repository

import com.yongjincompany.data.remote.datasource.RemoteChestDataSource
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.domain.entity.chests.*
import com.yongjincompany.domain.repository.ChestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChestRepositoryImpl @Inject constructor(
    private val remoteChestDataSource: RemoteChestDataSource,
) : ChestRepository {
    override suspend fun fetchFreeChestTime(): Flow<FetchFreeChestTimeEntity> =
        OfflineCacheUtil<FetchFreeChestTimeEntity>()
            .remoteData { remoteChestDataSource.fetchFreeChestTime() }
            .createRemoteFlow()

    override suspend fun fetchSpecialChestTime(): Flow<FetchSpecialChestTimeEntity> =
        OfflineCacheUtil<FetchSpecialChestTimeEntity>()
            .remoteData { remoteChestDataSource.fetchSpecialChestTime() }
            .createRemoteFlow()

    override suspend fun openFreeChest(): Flow<FreeChestOpenEntity> =
        OfflineCacheUtil<FreeChestOpenEntity>()
            .remoteData { remoteChestDataSource.openFreeChest() }
            .createRemoteFlow()

    override suspend fun openSpecialChest(): Flow<SpecialChestOpenEntity> =
        OfflineCacheUtil<SpecialChestOpenEntity>()
            .remoteData { remoteChestDataSource.openSpecialChest() }
            .createRemoteFlow()

    override suspend fun openSilverChest(): Flow<SilverChestOpenEntity> =
        OfflineCacheUtil<SilverChestOpenEntity>()
            .remoteData { remoteChestDataSource.openSilverChest() }
            .createRemoteFlow()

    override suspend fun openGoldChest(): Flow<GoldChestOpenEntity> =
        OfflineCacheUtil<GoldChestOpenEntity>()
            .remoteData { remoteChestDataSource.openGoldChest() }
            .createRemoteFlow()

    override suspend fun openLegendChest(): Flow<LegendChestOpenEntity> =
        OfflineCacheUtil<LegendChestOpenEntity>()
            .remoteData { remoteChestDataSource.openLegendChest() }
            .createRemoteFlow()
}
