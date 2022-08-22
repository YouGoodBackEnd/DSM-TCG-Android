package com.yongjincompany.data.remote.response.ranks

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.ranks.FetchRankEntity

data class FetchRankResponse(
    @SerializedName("rank_list") val rankList: List<Rank>,
) {
    data class Rank(
        @SerializedName("account_id") val accountId: String,
        @SerializedName("card_count") val cardCount: CardCount,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("user_id") val userId: Int,
    )

    data class CardCount(
        @SerializedName("agrade_card_count") val agradeCardCount: Int,
        @SerializedName("bgrade_card_count") val bgradeCardCount: Int,
        @SerializedName("cgrade_card_count") val cgradeCardCount: Int,
        @SerializedName("sgrade_card_count") val sgradeCardCount: Int,
        @SerializedName("ssgrade_card_count") val ssgradeCardCount: Int,
    )

    fun Rank.toEntity() =
        FetchRankEntity.Rank(
            accountId = accountId,
            cardCount = cardCount.toEntity(),
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId
        )

    fun CardCount.toEntity() =
        FetchRankEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            ssgradeCardCount = ssgradeCardCount
        )
}

fun FetchRankResponse.toEntity() =
    FetchRankEntity(
        rankList = rankList.map { it.toEntity() }
    )