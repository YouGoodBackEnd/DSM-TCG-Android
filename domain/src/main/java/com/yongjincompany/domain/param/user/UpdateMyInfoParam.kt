package com.yongjincompany.domain.param.user

import java.io.File

data class UpdateMyInfoParam(
    val name: String,
    val profileUrl: File?,
)
