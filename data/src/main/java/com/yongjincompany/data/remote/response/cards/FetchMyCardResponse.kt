package com.yongjincompany.data.remote.response.cards

import com.google.gson.annotations.SerializedName

data class FetchMyCardResponse(
    @SerializedName("card_count") val cardCount: CardCount,
    @SerializedName("card_list") val cardList: List<Card>
) {
    data class Card(
        @SerializedName("card_id") val cardId: Int,
        @SerializedName("card_image_url") val cardImageUrl: String,
        @SerializedName("count") val count: Int,
        @SerializedName("grade") val grade: String,
        @SerializedName("name") val name: String
    )
    data class CardCount(
        @SerializedName("agrade_card_count") val agradeCardCount: Int,
        @SerializedName("bgrade_card_count")val bgradeCardCount: Int,
        @SerializedName("cgrade_card_count")val cgradeCardCount: Int,
        @SerializedName("sgrade_card_count") val sgradeCardCount: Int,
        @SerializedName("ssgrade_card_count")val ssgradeCardCount: Int
    )
}