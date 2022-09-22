package com.yongjincompany.data.util

import org.threeten.bp.LocalDateTime

fun String.toLocalDateTime(): LocalDateTime =
    LocalDateTime.parse(this)