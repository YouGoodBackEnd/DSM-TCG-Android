package com.yongjincompany.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity

data class FetchMyInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("card_count") val cardCount: CardCount,
) {
    data class CardCount(
        @SerializedName("agrade_card_count") val agradeCardCount: Int,
        @SerializedName("bgrade_card_count") val bgradeCardCount: Int,
        @SerializedName("cgrade_card_count") val cgradeCardCount: Int,
        @SerializedName("sgrade_card_count") val sgradeCardCount: Int,
        @SerializedName("ssgrade_card_count") val ssgradeCardCount: Int,
    )

    fun CardCount.toEntity() =
        FetchMyInfoEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            ssgradeCardCount = ssgradeCardCount
        )
}

fun FetchMyInfoResponse.toEntity() =
    FetchMyInfoEntity(
        name = name,
        profileImageUrl = profileImageUrl,
        rank = rank,
        userId = userId,
        cardCount = cardCount.toEntity()
    )