package com.yongjincompany.data.local.datasource

import com.yongjincompany.data.local.storage.AuthDataStorage
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    //private val userDao: UserDao,
    private val authDataStorage: AuthDataStorage
) : LocalUserDataSource {
    override suspend fun setAccessToken(token: String) {
        authDataStorage.setAccessToken(token)
    }

    override suspend fun fetchAccessToken(): String =
        authDataStorage.fetchAccessToken()

    override suspend fun clearAccessToken() {
        authDataStorage.clearAccessToken()
    }

    override suspend fun setRefreshToken(token: String) {
        authDataStorage.setRefreshToken(token)
    }

    override suspend fun fetchRefreshToken(): String =
        authDataStorage.fetchRefreshToken()

    override suspend fun clearRefreshToken() {
        authDataStorage.clearRefreshToken()
    }

    override suspend fun setExpiredAt(localDateTime: String) {
        authDataStorage.setExpiredAt(localDateTime)
    }

    override suspend fun fetchExpiredAt(): LocalDateTime =
        authDataStorage.fetchExpiredAt()

    override suspend fun setId(id: String) {
        authDataStorage.setId(id)
    }

    override suspend fun fetchId(): String =
        authDataStorage.fetchId()

    override suspend fun clearId() {
        authDataStorage.clearId()
    }

    override suspend fun setPw(pw: String) {
        authDataStorage.setPw(pw)
    }

    override suspend fun fetchPw(): String =
        authDataStorage.fetchPw()

    override suspend fun clearPw() {
        authDataStorage.clearPw()
    }
}