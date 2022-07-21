package com.yongjincompany.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.yongjincompany.data.local.datasource.LocalUserDataSource
import com.yongjincompany.data.remote.datasource.RemoteUserDataSource
import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import com.yongjincompany.domain.param.PostUserRegisterParam
import com.yongjincompany.domain.param.PostUserSignInParam
import com.yongjincompany.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDateSource: RemoteUserDataSource,
) : UserRepository {
    override suspend fun postUserRegister(
        postUserRegisterParam: PostUserRegisterParam,
    ) {
        val postUserSignInParam = PostUserSignInParam(
            accountId = postUserRegisterParam.accountId,
            password = postUserRegisterParam.password
        )
        val response = remoteUserDateSource.postUserRegister(postUserRegisterParam.toRequest())
        saveAccount(postUserSignInParam)
        saveTokenSignUp(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            expiredAt = response.expiredAt
        )
    }

    override suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam,
    ) {
        val response = remoteUserDateSource.postUserSignIn(postUserSignInParam.toRequest())
        saveAccount(postUserSignInParam)
        saveToken(response)
    }

    override suspend fun autoLogin() {
        remoteUserDateSource.postUserSignIn(
            UserSignInRequest(
                localUserDataSource.fetchId(),
                localUserDataSource.fetchPw()
            )
        )
    }

    private suspend fun saveToken(userSignInResponse: UserSignInResponse) {
        localUserDataSource.apply {
            setAccessToken(userSignInResponse.accessToken)
            setRefreshToken(userSignInResponse.refreshToken)
            setExpiredAt(userSignInResponse.expiredAt)
        }
    }

    private suspend fun saveTokenSignUp(
        accessToken: String,
        refreshToken: String,
        expiredAt: String,
    ) {
        localUserDataSource.apply {
            setAccessToken(accessToken)
            setRefreshToken(refreshToken)
            setExpiredAt(expiredAt)
        }
    }

    private suspend fun saveAccount(userSignInParam: PostUserSignInParam) {
        localUserDataSource.apply {
            setId(userSignInParam.accountId)
            setPw(userSignInParam.password)
        }
    }

    fun PostUserRegisterParam.toRequest() =
        UserRegisterRequest(
            accountId = accountId,
            password = password,
            name = name
        )

    fun PostUserSignInParam.toRequest() =
        UserSignInRequest(
            accountId = accountId,
            password = password
        )
}