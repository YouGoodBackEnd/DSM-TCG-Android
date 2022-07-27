package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.request.users.ChangePasswordRequest
import com.yongjincompany.data.remote.request.users.UpdateMyInfoRequest
import com.yongjincompany.data.remote.request.users.UserRegisterRequest
import com.yongjincompany.data.remote.request.users.UserSignInRequest
import com.yongjincompany.data.remote.response.users.FetchMyInfoResponse
import com.yongjincompany.data.remote.response.users.UserRegisterResponse
import com.yongjincompany.data.remote.response.users.UserSignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
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

    @PATCH("users")
    suspend fun updateMyInfo(
        @Body updateMyInfoRequest: UpdateMyInfoRequest
    )

    @GET("users")
    suspend fun fetchMyInfo(
    ): FetchMyInfoResponse

    @PATCH("users/password")
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest
    )
}