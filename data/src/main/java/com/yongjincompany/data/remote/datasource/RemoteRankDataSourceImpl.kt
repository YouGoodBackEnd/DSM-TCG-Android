package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.RankApi
import com.yongjincompany.data.remote.response.ranks.FetchRankResponse
import com.yongjincompany.data.remote.response.ranks.toEntity
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import javax.inject.Inject

class RemoteRankDataSourceImpl @Inject constructor(
    private val rankApi: RankApi,
) : RemoteRankDataSource {
    override suspend fun fetchAllRank(): FetchRankEntity =
        HttpHandler<FetchRankResponse>()
            .httpRequest { rankApi.getAllRank() }
            .sendRequest().toEntity()

}