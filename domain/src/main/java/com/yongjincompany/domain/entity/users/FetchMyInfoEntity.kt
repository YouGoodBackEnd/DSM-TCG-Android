package com.yongjincompany.domain.entity.users

data class FetchMyInfoEntity(
    val name: String,
    val profileImageUrl: String,
    val rank: Int?,
    val userId: Long,
    val cardCount: CardCount,
) {
    data class CardCount(
        val agradeCardCount: Int,
        val bgradeCardCount: Int,
        val cgradeCardCount: Int,
        val sgradeCardCount: Int,
        val ssgradeCardCount: Int,
    )
}
