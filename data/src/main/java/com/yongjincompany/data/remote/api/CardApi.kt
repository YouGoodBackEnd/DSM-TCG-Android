package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.cards.FreeCardResponse
import retrofit2.http.POST

interface CardApi {
    @POST("cards/draw/free")
    suspend fun drawFreeCard(
    ) : FreeCardResponse
}