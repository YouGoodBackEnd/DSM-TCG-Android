package com.yongjincompany.data.local.datasource

import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import org.threeten.bp.LocalDateTime

interface LocalUserDataSource {
    suspend fun setAccessToken(token: String)
    suspend fun fetchAccessToken(): String
    suspend fun clearAccessToken()

    suspend fun setRefreshToken(token: String)
    suspend fun fetchRefreshToken(): String
    suspend fun clearRefreshToken()

    suspend fun setExpiredAt(localDateTime: String)
    suspend fun fetchExpiredAt(): LocalDateTime

    suspend fun setId(id: String)
    suspend fun fetchId(): String
    suspend fun clearId()

    suspend fun setPw(pw: String)
    suspend fun fetchPw(): String
    suspend fun clearPw()

    suspend fun fetchMyInfo(): FetchMyInfoEntity
    suspend fun insertMyInfo(fetchMyInfoEntity: FetchMyInfoEntity)
}