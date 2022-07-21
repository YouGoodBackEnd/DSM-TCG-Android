package com.yongjincompany.domain.usecase.user


import com.yongjincompany.domain.repository.UserRepository
import com.yongjincompany.domain.usecase.UseCase
import javax.inject.Inject

class AutoLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit): Unit =
        userRepository.autoLogin()
}