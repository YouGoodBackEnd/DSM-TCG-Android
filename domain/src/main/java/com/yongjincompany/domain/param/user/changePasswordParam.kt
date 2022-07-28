package com.yongjincompany.domain.param.user

data class ChangePasswordParam(
    val oldPassword: String,
    val newPassword: String
)
