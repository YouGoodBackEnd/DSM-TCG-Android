package com.yongjincompany.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity

data class FetchMyInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("profile_url") val profileUrl: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("user_id") val userId: Long,
    @SerializedName("account_id") val accountId: String
)

fun FetchMyInfoResponse.toEntity() =
    FetchMyInfoEntity(
        name = name,
        profileUrl = profileUrl,
        rank = rank,
        userId = userId,
        accountId = accountId
    )