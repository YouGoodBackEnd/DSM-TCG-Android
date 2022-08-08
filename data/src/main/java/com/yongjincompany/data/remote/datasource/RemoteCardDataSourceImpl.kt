package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.CardApi
import com.yongjincompany.data.remote.response.cards.FetchMyCardResponse
import com.yongjincompany.data.remote.response.cards.FreeCardResponse
import com.yongjincompany.data.remote.response.cards.toEntity
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import javax.inject.Inject

class RemoteCardDataSourceImpl @Inject constructor(
    private val cardApi: CardApi,
) : RemoteCardDataSource {
    override suspend fun drawFreeCard(): FreeCardResponse =
        HttpHandler<FreeCardResponse>()
            .httpRequest { cardApi.drawFreeCard() }
            .sendRequest()

    override suspend fun fetchMyCard(): FetchMyCardEntity =
        HttpHandler<FetchMyCardResponse>()
            .httpRequest { cardApi.fetchMyCard() }
            .sendRequest().toEntity()
}