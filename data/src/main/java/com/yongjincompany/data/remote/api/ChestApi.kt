package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.chests.*
import retrofit2.http.GET
import retrofit2.http.POST

interface ChestApi {
    @GET("chests/free")
    suspend fun getFreeChestTime(): FreeChestOpenTimeResponse

    @GET("chests/special")
    suspend fun getSpecialChestTime(): SpecialChestOpenTimeResponse

    @POST("chests/free")
    suspend fun openFreeChest(): FreeChestOpenResponse

    @POST("chests/special")
    suspend fun openSpecialChest(): SpecialChestOpenResponse

    @POST("chests/silver")
    suspend fun openSilverChest(): SilverChestOpenResponse

    @POST("chests/gold")
    suspend fun openGoldChest(): GoldChestOpenResponse
}