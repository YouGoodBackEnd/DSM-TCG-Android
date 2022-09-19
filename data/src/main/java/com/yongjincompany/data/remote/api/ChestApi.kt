package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.chests.FreeChestOpenResponse
import com.yongjincompany.data.remote.response.chests.FreeChestOpenTimeResponse
import com.yongjincompany.data.remote.response.chests.SpecialChestOpenTimeResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ChestApi {
    @GET("chests/free")
    suspend fun getFreeChestTime(): FreeChestOpenTimeResponse

    @GET("chests/special")
    suspend fun getSpecialChestTime(): SpecialChestOpenTimeResponse

    @POST("chests/free")
    suspend fun openFreeChest(): FreeChestOpenResponse
}