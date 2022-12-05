package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.chests.*
import com.yongjincompany.domain.param.chest.GoldChestOpenParam
import com.yongjincompany.domain.param.chest.LegendChestOpenParam
import com.yongjincompany.domain.param.chest.SilverChestOpenParam
import kotlinx.coroutines.flow.Flow

interface ChestRepository {
    suspend fun fetchFreeChestTime(): Flow<FetchFreeChestTimeEntity>

    suspend fun fetchSpecialChestTime(): Flow<FetchSpecialChestTimeEntity>

    suspend fun openFreeChest(): Flow<FreeChestOpenEntity>

    suspend fun openSpecialChest(): Flow<SpecialChestOpenEntity>

    suspend fun openSilverChest(silverChestOpenParam: SilverChestOpenParam): Flow<SilverChestOpenEntity>

    suspend fun openGoldChest(goldChestOpenParam: GoldChestOpenParam): Flow<GoldChestOpenEntity>

    suspend fun openLegendChest(legendChestOpenParam: LegendChestOpenParam): Flow<LegendChestOpenEntity>
}