package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.ChestApi
import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import com.yongjincompany.data.remote.response.chests.toEntity
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import javax.inject.Inject

class RemoteChestDataSourceImpl @Inject constructor(
    private val chestApi: ChestApi,
) : RemoteChestDataSource {
    override suspend fun fetchFreeChsetTime(): FetchFreeChestTimeEntity =
        HttpHandler<FreeChestOpenResponse>()
            .httpRequest { chestApi.getFreeChestTime() }
            .sendRequest().toEntity()
}