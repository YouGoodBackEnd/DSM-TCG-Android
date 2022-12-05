package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.request.chest.GoldChestOpenRequest
import com.yongjincompany.data.remote.request.chest.LegendChestOpenRequest
import com.yongjincompany.data.remote.request.chest.SilverChestOpenRequest
import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import com.yongjincompany.domain.entity.chests.*
import retrofit2.http.Body

interface RemoteChestDataSource {
    suspend fun fetchFreeChestTime(): FetchFreeChestTimeEntity

    suspend fun fetchSpecialChestTime(): FetchSpecialChestTimeEntity

    suspend fun openFreeChest(): FreeChestOpenEntity

    suspend fun openSpecialChest(): SpecialChestOpenEntity

    suspend fun openSilverChest(
        silverChestOpenRequest: SilverChestOpenRequest
    ): SilverChestOpenEntity

    suspend fun openGoldChest(
        goldChestOpenRequest: GoldChestOpenRequest
    ): GoldChestOpenEntity

    suspend fun openLegendChest(
        legendChestOpenRequest: LegendChestOpenRequest
    ): LegendChestOpenEntity
}