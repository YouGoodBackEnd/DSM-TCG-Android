package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.request.chest.GoldChestOpenRequest
import com.yongjincompany.data.remote.request.chest.LegendChestOpenRequest
import com.yongjincompany.data.remote.request.chest.SilverChestOpenRequest
import com.yongjincompany.data.remote.response.chests.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
    suspend fun openSilverChest(
        @Body silverChestOpenRequest : SilverChestOpenRequest
    ): SilverChestOpenResponse

    @POST("chests/gold")
    suspend fun openGoldChest(
        @Body goldChestOpenRequest: GoldChestOpenRequest
    ): GoldChestOpenResponse

    @POST("chests/legend")
    suspend fun openLegendChest(
        @Body legendChestOpenRequest: LegendChestOpenRequest
    ): LegendChestOpenResponse
}