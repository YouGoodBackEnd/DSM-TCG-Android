package com.yongjincompany.data.remote.response.chests

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.chests.GoldChestOpenEntity

data class GoldChestOpenResponse(
    @SerializedName("card_list") val cardList: List<Card>,
    @SerializedName("coin") val coin: Int,
    @SerializedName("diamond") val diamond: Int,
) {
    data class Card(
        @SerializedName("card_image_url") val cardImageUrl: String,
        @SerializedName("card_name") val cardName: String,
        @SerializedName("description") val description: String,
        @SerializedName("grade") val grade: String,
        @SerializedName("id") val id: Int,
    )

}

fun GoldChestOpenResponse.toEntity() =
    GoldChestOpenEntity(
        cardList = cardList.map { it.toEntity() },
        coin = coin,
        diamond = diamond
    )

fun GoldChestOpenResponse.Card.toEntity() =
    GoldChestOpenEntity.Card(
        cardImageUrl = cardImageUrl,
        cardName = cardName,
        description = description,
        grade = grade,
        id = id
    )


