package com.yongjincompany.data.remote.response.cards

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity

data class FetchMyCardResponse(
    @SerializedName("card_count") val cardCount: CardCount,
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("card_list") val cardList: List<Card>,
) {
    data class Card(
        @SerializedName("card_id") val cardId: Int,
        @SerializedName("card_image_url") val cardImageUrl: String,
        @SerializedName("count") val count: Int,
        @SerializedName("grade") val grade: String,
        @SerializedName("description") val description: String,
        @SerializedName("name") val name: String,
    )

    data class CardCount(
        @SerializedName("agrade_card_count") val agradeCardCount: Int,
        @SerializedName("bgrade_card_count") val bgradeCardCount: Int,
        @SerializedName("cgrade_card_count") val cgradeCardCount: Int,
        @SerializedName("sgrade_card_count") val sgradeCardCount: Int,
        @SerializedName("ssgrade_card_count") val ssgradeCardCount: Int,
    )

    fun Card.toEntity() =
        FetchMyCardEntity.Card(
            cardId = cardId,
            cardImageUrl = cardImageUrl,
            count = count,
            description = description,
            grade = grade,
            name = name
        )

    fun CardCount.toEntity() =
        FetchMyCardEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            ssgradeCardCount = ssgradeCardCount
        )
}

fun FetchMyCardResponse.toEntity() =
    FetchMyCardEntity(
        cardCount = cardCount.toEntity(),
        totalCount = totalCount,
        cardList = cardList.map { it.toEntity() }
    )