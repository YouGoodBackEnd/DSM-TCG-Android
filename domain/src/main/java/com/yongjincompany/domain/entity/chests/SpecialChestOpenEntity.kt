package com.yongjincompany.domain.entity.chests

data class SpecialChestOpenEntity(
    val cardList: List<Card>,
    val coin: Int,
    val diamond: Int,
) {
    data class Card(
        val cardImageUrl: String,
        val cardName: String,
        val description: String,
        val grade: String,
        val id: Int,
    )
}