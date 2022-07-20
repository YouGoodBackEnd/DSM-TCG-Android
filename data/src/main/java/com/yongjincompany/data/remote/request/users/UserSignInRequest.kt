package com.yongjincompany.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UserSignInRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String
)