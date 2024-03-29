package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.ranks.FetchRankResponse
import retrofit2.http.GET

interface RankApi {
    @GET("ranks")
    suspend fun getAllRank(): FetchRankResponse
}