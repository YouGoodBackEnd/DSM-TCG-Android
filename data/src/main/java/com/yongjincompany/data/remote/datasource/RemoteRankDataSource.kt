package com.yongjincompany.data.remote.datasource

import com.yongjincompany.domain.entity.ranks.FetchRankEntity

interface RemoteRankDataSource {
    suspend fun fetchAllRank(): FetchRankEntity
}