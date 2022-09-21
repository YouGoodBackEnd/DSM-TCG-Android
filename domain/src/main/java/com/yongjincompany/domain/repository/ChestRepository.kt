package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
import com.yongjincompany.domain.entity.chests.FreeChestOpenEntity
import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity
import kotlinx.coroutines.flow.Flow

interface ChestRepository {
    suspend fun fetchFreeChestTime(): Flow<FetchFreeChestTimeEntity>

    suspend fun fetchSpecialChestTime(): Flow<FetchSpecialChestTimeEntity>

    suspend fun openFreeChest(): Flow<FreeChestOpenEntity>

    suspend fun openSpecialChest(): Flow<SpecialChestOpenEntity>
}