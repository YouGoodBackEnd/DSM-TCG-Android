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
    val profileUrl: String,
    val rank: Int,
    @Embedded val cardCount: CardCount
) {
    data class CardCount(
        val agradeCardCount: Int,
        val bgradeCardCount: Int,
        val cgradeCardCount: Int,
        val sgradeCardCount: Int,
        val ssgradeCardCount: Int,
    )

    fun FetchMyInfoRoomEntity.CardCount.toEntity() =
        FetchMyInfoEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            ssgradeCardCount = ssgradeCardCount
        )
}
fun FetchMyInfoEntity.CardCount.toDbEntity() =
    FetchMyInfoRoomEntity.CardCount(
        agradeCardCount = agradeCardCount,
        bgradeCardCount = bgradeCardCount,
        cgradeCardCount = cgradeCardCount,
        sgradeCardCount = sgradeCardCount,
        ssgradeCardCount = ssgradeCardCount
    )

fun FetchMyInfoRoomEntity.toEntity() =
    FetchMyInfoEntity(
        name = name,
        profileUrl = profileUrl,
        rank = rank,
        userId = userId,
        cardCount = cardCount.toEntity()
    )

fun FetchMyInfoEntity.toDbEntity() =
    FetchMyInfoRoomEntity(
        name = name,
        profileUrl = profileUrl,
        rank = rank,
        userId = userId,
        cardCount = cardCount.toDbEntity()
    )