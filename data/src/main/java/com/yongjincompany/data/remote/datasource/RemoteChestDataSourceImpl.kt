package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.ChestApi
import com.yongjincompany.data.remote.request.chest.GoldChestOpenRequest
import com.yongjincompany.data.remote.request.chest.LegendChestOpenRequest
import com.yongjincompany.data.remote.request.chest.SilverChestOpenRequest
import com.yongjincompany.data.remote.response.chests.*
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.chests.*
import javax.inject.Inject

class RemoteChestDataSourceImpl @Inject constructor(
    private val chestApi: ChestApi,
) : RemoteChestDataSource {
    override suspend fun fetchFreeChestTime(): FetchFreeChestTimeEntity =
        HttpHandler<FreeChestOpenTimeResponse>()
            .httpRequest { chestApi.getFreeChestTime() }
            .sendRequest().toEntity()

    override suspend fun fetchSpecialChestTime(): FetchSpecialChestTimeEntity =
        HttpHandler<SpecialChestOpenTimeResponse>()
            .httpRequest { chestApi.getSpecialChestTime() }
            .sendRequest().toEntity()

    override suspend fun openFreeChest(): FreeChestOpenEntity =
        HttpHandler<FreeChestOpenResponse>()
            .httpRequest { chestApi.openFreeChest() }
            .sendRequest().toEntity()

    override suspend fun openSpecialChest(): SpecialChestOpenEntity =
        HttpHandler<SpecialChestOpenResponse>()
            .httpRequest { chestApi.openSpecialChest() }
            .sendRequest().toEntity()

    override suspend fun openSilverChest(silverChestOpenRequest: SilverChestOpenRequest): SilverChestOpenEntity =
        HttpHandler<SilverChestOpenResponse>()
            .httpRequest { chestApi.openSilverChest(silverChestOpenRequest) }
            .sendRequest().toEntity()

    override suspend fun openGoldChest(goldChestOpenRequest: GoldChestOpenRequest): GoldChestOpenEntity =
        HttpHandler<GoldChestOpenResponse>()
            .httpRequest { chestApi.openGoldChest(goldChestOpenRequest) }
            .sendRequest().toEntity()

    override suspend fun openLegendChest(legendChestOpenRequest: LegendChestOpenRequest): LegendChestOpenEntity =
        HttpHandler<LegendChestOpenResponse>()
            .httpRequest { chestApi.openLegendChest(legendChestOpenRequest) }
            .sendRequest().toEntity()
}