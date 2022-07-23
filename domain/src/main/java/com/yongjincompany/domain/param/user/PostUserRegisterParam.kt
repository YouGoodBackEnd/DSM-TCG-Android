package com.yongjincompany.domain.param.user

data class PostUserRegisterParam(
    val accountId: String,
    val name: String,
    val password: String,
)