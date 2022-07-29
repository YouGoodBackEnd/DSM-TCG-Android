package com.yongjincompany.domain.usecase.user

import com.yongjincompany.domain.param.user.ChangePasswordParam
import com.yongjincompany.domain.repository.UserRepository
import com.yongjincompany.domain.usecase.UseCase
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<ChangePasswordParam, Unit>() {

    override suspend fun execute(data: ChangePasswordParam) =
        userRepository.changePassword(data)
}