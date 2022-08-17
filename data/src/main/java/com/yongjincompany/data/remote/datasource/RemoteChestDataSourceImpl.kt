package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.ChestApi
import com.yongjincompany.data.remote.response.chests.FreeChestOpenTimeResponse
import com.yongjincompany.data.remote.response.chests.SpecialChestOpenTimeResponse
import com.yongjincompany.data.remote.response.chests.toEntity
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
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
}