package com.yongjincompany.domain.entity.ranks

data class FetchRankEntity(
    val rankList: List<Rank>,
) {
    data class Rank(
        val accountId: String,
        val cardCount: CardCount,
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
    )

    data class CardCount(
        val agradeCardCount: Int,
        val bgradeCardCount: Int,
        val cgradeCardCount: Int,
        val sgradeCardCount: Int,
        val ssgradeCardCount: Int,
    )
}
