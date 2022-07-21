package com.yongjincompany.domain.usecase.user


import com.yongjincompany.domain.param.PostUserRegisterParam
import com.yongjincompany.domain.repository.UserRepository
import com.yongjincompany.domain.usecase.UseCase
import javax.inject.Inject

class PostUserRegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PostUserRegisterParam, Unit>() {

    override suspend fun execute(data: PostUserRegisterParam) =
        userRepository.postUserRegister(data)
}