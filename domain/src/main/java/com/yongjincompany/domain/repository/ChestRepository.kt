package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.chests.*
import kotlinx.coroutines.flow.Flow

interface ChestRepository {
    suspend fun fetchFreeChestTime(): Flow<FetchFreeChestTimeEntity>

    suspend fun fetchSpecialChestTime(): Flow<FetchSpecialChestTimeEntity>

    suspend fun openFreeChest(): Flow<FreeChestOpenEntity>

    suspend fun openSpecialChest(): Flow<SpecialChestOpenEntity>

    suspend fun openSilverChest(): Flow<SilverChestOpenEntity>

    suspend fun openGoldChest(): Flow<GoldChestOpenEntity>
}