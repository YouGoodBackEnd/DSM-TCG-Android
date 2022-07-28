package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.request.users.ChangePasswordRequest
import com.yongjincompany.data.remote.request.users.UpdateMyInfoRequest
import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.response.users.UserRegisterResponse
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity

interface RemoteUserDataSource {

    suspend fun postUserRegister(
        userRegisterRequest: UserRegisterRequest,
    ): UserRegisterResponse

    suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest,
    ): UserSignInResponse

    suspend fun updateMyInfo(
        updateMyInfoRequest: UpdateMyInfoRequest,
    )

    suspend fun fetchMyInfo(
    ): FetchMyInfoEntity

    suspend fun changePassword(
        changePasswordRequest: ChangePasswordRequest
    )
}