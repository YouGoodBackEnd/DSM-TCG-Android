package com.yongjincompany.data.remote.datasource

import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity

interface RemoteChestDataSource {
    suspend fun fetchFreeChsetTime():FetchFreeChestTimeEntity
}