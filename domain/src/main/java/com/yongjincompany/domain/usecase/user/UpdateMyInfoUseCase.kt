package com.yongjincompany.domain.usecase.user

import com.yongjincompany.domain.param.user.UpdateMyInfoParam
import com.yongjincompany.domain.repository.UserRepository
import com.yongjincompany.domain.usecase.UseCase
import javax.inject.Inject

class UpdateMyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<UpdateMyInfoParam, Unit>() {

    override suspend fun execute(data: UpdateMyInfoParam) =
        userRepository.updateMyInfo(data)
}