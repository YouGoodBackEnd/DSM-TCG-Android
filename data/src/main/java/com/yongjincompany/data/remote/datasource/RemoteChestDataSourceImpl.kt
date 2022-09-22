package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.ChestApi
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

    override suspend fun openSilverChest(): SilverChestOpenEntity =
        HttpHandler<SilverChestOpenResponse>()
            .httpRequest { chestApi.openSilverChest() }
            .sendRequest().toEntity()

    override suspend fun openGoldChest(): GoldChestOpenEntity =
        HttpHandler<GoldChestOpenResponse>()
            .httpRequest { chestApi.openGoldChest() }
            .sendRequest().toEntity()
}