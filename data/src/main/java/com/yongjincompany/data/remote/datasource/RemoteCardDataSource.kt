package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.response.cards.FetchMyCardItemResponse
import com.yongjincompany.data.remote.response.cards.FreeCardResponse

interface RemoteCardDataSource {
    suspend fun drawFreeCard(): FreeCardResponse

    suspend fun fetchMyCard(): List<FetchMyCardItemResponse>
}