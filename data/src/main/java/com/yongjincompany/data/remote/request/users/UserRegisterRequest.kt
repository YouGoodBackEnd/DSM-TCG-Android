package com.yongjincompany.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UserRegisterRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)