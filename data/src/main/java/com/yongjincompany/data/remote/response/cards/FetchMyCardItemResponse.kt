package com.yongjincompany.data.remote.response.cards

import com.google.gson.annotations.SerializedName

    data class FetchMyCardItemResponse(
        @SerializedName("card_id") val cardId: Int,
        @SerializedName("card_image_url") val cardImageUrl: String,
        @SerializedName("count") val count: Int,
        @SerializedName("grade") val grade: String,
        @SerializedName("name") val name: String,
    )