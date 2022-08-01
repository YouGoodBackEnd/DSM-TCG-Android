package com.yongjincompany.data.remote.response.cards

import com.google.gson.annotations.SerializedName

data class FreeCardResponse(
    @SerializedName("card_list") val cardList: List<Card>,
    @SerializedName("coin") val coin: Int,
    @SerializedName("diamond") val diamond: Int
) {
    data class Card(
        @SerializedName("card_image_url") val cardImageUrl: String,
        @SerializedName("card_name") val cardName: String,
        @SerializedName("description") val description: String,
        @SerializedName("grade") val grade: String,
        @SerializedName("id") val id: Int
    )
}