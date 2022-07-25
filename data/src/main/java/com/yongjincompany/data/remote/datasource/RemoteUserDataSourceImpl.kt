package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.UserApi
import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.request.users.updateMyInfoRequest
import com.yongjincompany.data.remote.response.users.UserRegisterResponse
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import com.yongjincompany.data.util.HttpHandler
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
) : RemoteUserDataSource {
    override suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest) =
        HttpHandler<UserRegisterResponse>()
            .httpRequest { userApi.userRegister(userRegisterRequest) }
            .sendRequest()

    override suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest,
    ) = HttpHandler<UserSignInResponse>()
        .httpRequest { userApi.userSignIn(userSignInRequest) }
        .sendRequest()

    override suspend fun updateMyInfo(updateMyInfoRequest: updateMyInfoRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.updateMyInfo(updateMyInfoRequest) }
            .sendRequest()
}