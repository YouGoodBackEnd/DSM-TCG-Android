package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import kotlinx.coroutines.flow.Flow

interface ChestRepository {
    suspend fun fetchFreeChestTime(): Flow<FetchFreeChestTimeEntity>
}