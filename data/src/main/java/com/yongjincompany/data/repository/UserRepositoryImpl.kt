package com.yongjincompany.data.repository

import com.yongjincompany.data.local.datasource.LocalUserDataSource
import com.yongjincompany.data.remote.datasource.RemoteImagesDataSource
import com.yongjincompany.data.remote.datasource.RemoteUserDataSource
import com.yongjincompany.data.remote.request.users.ChangePasswordRequest
import com.yongjincompany.data.remote.request.users.UpdateMyInfoRequest
import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.data.util.toMultipart
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.param.user.ChangePasswordParam
import com.yongjincompany.domain.param.user.PostUserRegisterParam
import com.yongjincompany.domain.param.user.PostUserSignInParam
import com.yongjincompany.domain.param.user.UpdateMyInfoParam
import com.yongjincompany.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDateSource: RemoteUserDataSource,
    private val remoteImagesDataSource: RemoteImagesDataSource,
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

    override suspend fun updateMyInfo(updateMyInfoParam: UpdateMyInfoParam) {
        val imageUrl = if (updateMyInfoParam.profileUrl != null) {
            remoteImagesDataSource.postImages(
                listOf(updateMyInfoParam.profileUrl!!.toMultipart())
            ).imageUrl.first()
        } else ""

        remoteUserDateSource.updateMyInfo(updateMyInfoParam.toRequest(imageUrl))
    }

    override suspend fun fetchMyInfo(): Flow<FetchMyInfoEntity> =
        OfflineCacheUtil<FetchMyInfoEntity>()
            .remoteData { remoteUserDateSource.fetchMyInfo() }
            .localData { localUserDataSource.fetchMyInfo() }
            .doOnNeedRefresh { localUserDataSource.insertMyInfo(it) }
            .createFlow()

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

    override suspend fun changePassword(
        changePasswordParam: ChangePasswordParam
    ) {
        remoteUserDateSource.changePassword(changePasswordParam.toRequest())
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

    fun UpdateMyInfoParam.toRequest(profileUrl: String) =
        UpdateMyInfoRequest(
            name = name,
            profileUrl = profileUrl
        )

    fun ChangePasswordParam.toRequest() =
        ChangePasswordRequest(
            oldPassword = oldPassword,
            newPassword = newPassword
        )

}