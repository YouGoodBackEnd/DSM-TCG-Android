package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.response.cards.FetchMyCardResponse
import com.yongjincompany.data.remote.response.cards.FreeCardResponse
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity

interface RemoteCardDataSource {
    suspend fun drawFreeCard(): FreeCardResponse

    suspend fun fetchMyCard(): FetchMyCardEntity
}