package com.yongjincompany.domain.entity.cards

data class FetchMyCardEntity(
    val cardCount: CardCount,
    val totalCount: Int,
    val cardList: List<Card>,
) {
    data class Card(
        val cardId: Int,
        val cardImageUrl: String,
        val count: Int,
        val description: String,
        val grade: String,
        val name: String,
    )

    data class CardCount(
        val agradeCardCount: Int,
        val bgradeCardCount: Int,
        val cgradeCardCount: Int,
        val sgradeCardCount: Int,
        val ssgradeCardCount: Int,
    )
}
