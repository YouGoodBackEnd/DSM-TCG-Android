package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.request.users.updateMyInfoRequest
import com.yongjincompany.data.remote.response.users.UserRegisterResponse
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserApi {
    @POST("users")
    suspend fun userRegister(
        @Body userSignUpRequest: UserRegisterRequest
    ): UserRegisterResponse

    @POST("users/auth")
    suspend fun userSignIn(
        @Body userSignInRequest: UserSignInRequest
    ): UserSignInResponse

    @PATCH
    suspend fun updateMyInfo(
        @Body updateMyInfoRequest: updateMyInfoRequest
    )
}