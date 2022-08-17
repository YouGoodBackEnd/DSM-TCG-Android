package com.yongjincompany.data.remote.datasource

import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity

interface RemoteChestDataSource {
    suspend fun fetchFreeChestTime(): FetchFreeChestTimeEntity

    suspend fun fetchSpecialChestTime(): FetchSpecialChestTimeEntity
}