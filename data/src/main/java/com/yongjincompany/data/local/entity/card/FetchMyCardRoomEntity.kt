package com.yongjincompany.data.local.entity.card

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity

@Entity(tableName = "myCard")
data class FetchMyCardRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val cardCount: CardCount,
    val cardList: List<Card>,
) {
    data class Card(
        val cardId: Int,
        val cardImageUrl: String,
        val count: Int,
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

    fun Card.toEntity() =
        FetchMyCardEntity.Card(
            cardId = cardId,
            cardImageUrl = cardImageUrl,
            count = count,
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

fun FetchMyCardEntity.Card.toDbEntity() =
    FetchMyCardRoomEntity.Card(
        cardId = cardId,
        cardImageUrl = cardImageUrl,
        count = count,
        grade = grade,
        name = name
    )

fun FetchMyCardEntity.CardCount.toDbEntity() =
    FetchMyCardRoomEntity.CardCount(
        agradeCardCount = agradeCardCount,
        bgradeCardCount = bgradeCardCount,
        cgradeCardCount = cgradeCardCount,
        sgradeCardCount = sgradeCardCount,
        ssgradeCardCount = ssgradeCardCount
    )

fun FetchMyCardRoomEntity.toEntity() =
    FetchMyCardEntity(
        cardCount = cardCount.toEntity(),
        cardList = cardList.map { it.toEntity() }
    )

fun FetchMyCardEntity.toDbEntity() =
    FetchMyCardRoomEntity(
        cardCount = cardCount.toDbEntity(),
        cardList = cardList.map { it.toDbEntity() }
    )
