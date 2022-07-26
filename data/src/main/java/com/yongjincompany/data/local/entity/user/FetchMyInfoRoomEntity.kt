package com.yongjincompany.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity

@Entity(tableName = "myInfo")
data class FetchMyInfoRoomEntity(
    @PrimaryKey val userId: Long,
    @PrimaryKey val accountId: String,
    val name: String,
    val profileUrl: String,
    val rank: Int,
)

fun FetchMyInfoRoomEntity.toEntity() =
    FetchMyInfoEntity(
        name = name,
        profileUrl = profileUrl,
        rank = rank,
        userId = userId,
        accountId = accountId
    )

fun FetchMyInfoEntity.toDbEntity() =
    FetchMyInfoRoomEntity(
        name = name,
        profileUrl = profileUrl,
        rank = rank,
        userId = userId,
        accountId = accountId
    )