package com.yongjincompany.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yongjincompany.data.local.entity.card.FetchMyCardRoomEntity
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity

@Entity(tableName = "myInfo")
data class FetchMyInfoRoomEntity(
    @PrimaryKey val userId: Long,
    val name: String,
    val profileImageUrl: String,
    val rank: Int,
    val coin: Int,
    @Embedded val cardCount: CardCount
) {
    data class CardCount(
        val agradeCardCount: Int,
        val bgradeCardCount: Int,
        val cgradeCardCount: Int,
        val sgradeCardCount: Int,
        val dgradeCardCount: Int,
    )

    fun CardCount.toEntity() =
        FetchMyInfoEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            dgradeCardCount = dgradeCardCount
        )
}
fun FetchMyInfoEntity.CardCount.toDbEntity() =
    FetchMyInfoRoomEntity.CardCount(
        agradeCardCount = agradeCardCount,
        bgradeCardCount = bgradeCardCount,
        cgradeCardCount = cgradeCardCount,
        sgradeCardCount = sgradeCardCount,
        dgradeCardCount = dgradeCardCount
    )

fun FetchMyInfoRoomEntity.toEntity() =
    FetchMyInfoEntity(
        name = name,
        profileImageUrl = profileImageUrl,
        rank = rank,
        coin = coin,
        userId = userId,
        cardCount = cardCount.toEntity()
    )

fun FetchMyInfoEntity.toDbEntity() =
    rank?.let {
        FetchMyInfoRoomEntity(
            name = name,
            profileImageUrl = profileImageUrl,
            rank = rank!!,
            coin = coin,
            userId = userId,
            cardCount = cardCount.toDbEntity()
        )
    }