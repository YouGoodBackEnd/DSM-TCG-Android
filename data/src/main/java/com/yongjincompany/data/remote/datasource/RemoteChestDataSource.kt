package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import com.yongjincompany.domain.entity.chests.*

interface RemoteChestDataSource {
    suspend fun fetchFreeChestTime(): FetchFreeChestTimeEntity

    suspend fun fetchSpecialChestTime(): FetchSpecialChestTimeEntity

    suspend fun openFreeChest(): FreeChestOpenEntity

    suspend fun openSpecialChest(): SpecialChestOpenEntity

    suspend fun openSilverChest(): SilverChestOpenEntity

    suspend fun openGoldChest(): GoldChestOpenEntity

    suspend fun openLegendChest(): LegendChestOpenEntity
}