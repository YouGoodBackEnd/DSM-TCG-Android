package com.yongjincompany.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class UserRegisterResponse(
    @SerializedName("access_token")val accessToken: String,
    @SerializedName("expired_at") val expiredAt: String,
    @SerializedName("refresh_token") val refreshToken: String
)