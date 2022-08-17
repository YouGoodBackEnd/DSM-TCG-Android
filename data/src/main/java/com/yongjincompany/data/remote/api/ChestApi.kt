package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import retrofit2.http.GET

interface ChestApi {
    @GET("chests/free")
    suspend fun getFreeChestTime(): FreeChestOpenResponse
}