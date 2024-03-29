package com.yongjincompany.data.repository

import com.yongjincompany.data.remote.datasource.RemoteChestDataSource
import com.yongjincompany.data.remote.request.chest.GoldChestOpenRequest
import com.yongjincompany.data.remote.request.chest.LegendChestOpenRequest
import com.yongjincompany.data.remote.request.chest.SilverChestOpenRequest
import com.yongjincompany.data.remote.request.users.ChangePasswordRequest
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.domain.entity.chests.*
import com.yongjincompany.domain.param.chest.GoldChestOpenParam
import com.yongjincompany.domain.param.chest.LegendChestOpenParam
import com.yongjincompany.domain.param.chest.SilverChestOpenParam
import com.yongjincompany.domain.param.user.ChangePasswordParam
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

    override suspend fun openSilverChest(silverChestOpenParam: SilverChestOpenParam): Flow<SilverChestOpenEntity> =
        OfflineCacheUtil<SilverChestOpenEntity>()
            .remoteData { remoteChestDataSource.openSilverChest(silverChestOpenParam.toRequest()) }
            .createRemoteFlow()

    override suspend fun openGoldChest(goldChestOpenParam: GoldChestOpenParam): Flow<GoldChestOpenEntity> =
        OfflineCacheUtil<GoldChestOpenEntity>()
            .remoteData { remoteChestDataSource.openGoldChest(goldChestOpenParam.toRequest()) }
            .createRemoteFlow()

    override suspend fun openLegendChest(legendChestOpenParam: LegendChestOpenParam): Flow<LegendChestOpenEntity> =
        OfflineCacheUtil<LegendChestOpenEntity>()
            .remoteData { remoteChestDataSource.openLegendChest(legendChestOpenParam.toRequest()) }
            .createRemoteFlow()

    fun SilverChestOpenParam.toRequest() =
        SilverChestOpenRequest(
            price = price
        )

    fun GoldChestOpenParam.toRequest() =
        GoldChestOpenRequest(
            price = price
        )

    fun LegendChestOpenParam.toRequest() =
        LegendChestOpenRequest(
            price = price
        )
}

