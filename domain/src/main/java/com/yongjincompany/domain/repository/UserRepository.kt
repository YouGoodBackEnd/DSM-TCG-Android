package com.yongjincompany.domain.repository

import com.yongjincompany.domain.param.user.PostUserRegisterParam
import com.yongjincompany.domain.param.user.PostUserSignInParam
import com.yongjincompany.domain.param.user.UpdateMyInfoParam

interface UserRepository {
    suspend fun postUserRegister(
        postUserRegisterParam: PostUserRegisterParam
    )

    suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    )

    suspend fun autoLogin()

    suspend fun updateMyInfo(
        updateMyInfoParam: UpdateMyInfoParam
    )
}