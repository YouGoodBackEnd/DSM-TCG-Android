package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.response.users.UserRegisterResponse
import com.yongjincompany.data.remote.response.users.UserSignInResponse

interface RemoteUserDataSource {

    suspend fun postUserRegister(
        userRegisterRequest: UserRegisterRequest
    ): UserRegisterResponse

    suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest
    ): UserSignInResponse
}