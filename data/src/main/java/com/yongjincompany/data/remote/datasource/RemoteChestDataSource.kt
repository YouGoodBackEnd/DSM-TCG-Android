package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
import com.yongjincompany.domain.entity.chests.FreeChestOpenEntity
import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity

interface RemoteChestDataSource {
    suspend fun fetchFreeChestTime(): FetchFreeChestTimeEntity

    suspend fun fetchSpecialChestTime(): FetchSpecialChestTimeEntity

    suspend fun openFreeChest(): FreeChestOpenEntity

    suspend fun openSpecialChest(): SpecialChestOpenEntity
}