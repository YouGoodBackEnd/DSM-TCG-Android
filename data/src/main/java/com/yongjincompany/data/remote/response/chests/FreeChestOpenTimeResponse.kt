package com.yongjincompany.data.remote.response.chests

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity

data class FreeChestOpenTimeResponse(
    @SerializedName("chest_open_date_time") val chestOpenDateTime: String,
    @SerializedName("is_opened") val isOpened: Boolean,
)

fun FreeChestOpenTimeResponse.toEntity() =
    FetchFreeChestTimeEntity(
        chestOpenDateTime = chestOpenDateTime,
        isOpened = isOpened
    )