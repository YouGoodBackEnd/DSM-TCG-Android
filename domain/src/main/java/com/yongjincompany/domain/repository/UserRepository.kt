package com.yongjincompany.domain.repository

import com.yongjincompany.domain.param.PostUserRegisterParam
import com.yongjincompany.domain.param.PostUserSignInParam

interface UserRepository {
    suspend fun postUserRegister(
        postUserRegisterParam: PostUserRegisterParam
    )

    suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    )

    suspend fun autoLogin()
}