package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import com.yongjincompany.data.remote.response.chests.SpecialChestOpenResponse
import retrofit2.http.GET

interface ChestApi {
    @GET("chests/free")
    suspend fun getFreeChestTime(): FreeChestOpenResponse

    @GET("chests/special")
    suspend fun getSpecialChestTime(): SpecialChestOpenResponse
}