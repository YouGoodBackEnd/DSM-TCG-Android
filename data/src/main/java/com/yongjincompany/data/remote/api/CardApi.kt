package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.cards.FetchMyCardResponse
import com.yongjincompany.data.remote.response.cards.FreeCardResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface CardApi {
    @GET("cards")
    suspend fun fetchMyCard(
    ): FetchMyCardResponse
}