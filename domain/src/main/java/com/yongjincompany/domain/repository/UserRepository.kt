package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.param.user.ChangePasswordParam
import com.yongjincompany.domain.param.user.PostUserRegisterParam
import com.yongjincompany.domain.param.user.PostUserSignInParam
import com.yongjincompany.domain.param.user.UpdateMyInfoParam
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun postUserRegister(
        postUserRegisterParam: PostUserRegisterParam,
    )

    suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam,
    )

    suspend fun autoLogin()

    suspend fun updateMyInfo(
        updateMyInfoParam: UpdateMyInfoParam,
    )

    suspend fun fetchMyInfo(): Flow<FetchMyInfoEntity>

    suspend fun changePassword(
        changePasswordParam: ChangePasswordParam
    )
}