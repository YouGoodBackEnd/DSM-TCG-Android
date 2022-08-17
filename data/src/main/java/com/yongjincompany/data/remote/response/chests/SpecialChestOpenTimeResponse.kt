package com.yongjincompany.data.remote.response.chests

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity

data class SpecialChestOpenTimeResponse(
    @SerializedName("chest_open_date_time") val chestOpenDateTime: String,
    @SerializedName("is_opened") val isOpened: Boolean,
)

fun SpecialChestOpenTimeResponse.toEntity() =
    FetchSpecialChestTimeEntity(
        chestOpenDateTime = chestOpenDateTime,
        isOpened = isOpened
    )
