package com.yongjincompany.data.remote.response.users

data class FetchMyInfoResponse(
    val name: String,
    val profile_url: String,
    val rank: Int,
    val user_id: String
)