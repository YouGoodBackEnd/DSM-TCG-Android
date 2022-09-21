package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.ChestApi
import com.yongjincompany.data.remote.response.chests.*
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
import com.yongjincompany.domain.entity.chests.FreeChestOpenEntity
import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity
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

}