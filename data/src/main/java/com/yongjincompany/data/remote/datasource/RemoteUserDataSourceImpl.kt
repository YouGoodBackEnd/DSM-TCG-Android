package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.UserApi
import com.yongjincompany.data.remote.request.users.ChangePasswordRequest
import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.request.users.UpdateMyInfoRequest
import com.yongjincompany.data.remote.response.users.FetchMyInfoResponse
import com.yongjincompany.data.remote.response.users.UserRegisterResponse
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import com.yongjincompany.data.remote.response.users.toEntity
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
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

    override suspend fun updateMyInfo(updateMyInfoRequest: UpdateMyInfoRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.updateMyInfo(updateMyInfoRequest) }
            .sendRequest()

    override suspend fun fetchMyInfo(): FetchMyInfoEntity =
        HttpHandler<FetchMyInfoResponse>()
            .httpRequest { userApi.fetchMyInfo() }
            .sendRequest().toEntity()

    override suspend fun changePassword(changePasswordRequest: ChangePasswordRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.changePassword(changePasswordRequest) }
            .sendRequest()

    override suspend fun logOut() {
        HttpHandler<Unit>()
            .httpRequest { userApi.logOut() }
            .sendRequest()
    }

    override suspend fun deleteAccount() {
        HttpHandler<Unit>()
            .httpRequest { userApi.deleteAccount() }
            .sendRequest()
    }
}