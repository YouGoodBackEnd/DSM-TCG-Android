package com.yongjincompany.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FetchMyInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("profile_url") val profileUrl: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("user_id") val userId: String
)