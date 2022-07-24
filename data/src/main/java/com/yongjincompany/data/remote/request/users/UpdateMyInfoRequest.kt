package com.yongjincompany.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UpdateMyInfoRequest(
    @SerializedName("name") val name: String,
    @SerializedName("profileUrl") val profileUrl: String,
)