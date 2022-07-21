package com.yongjincompany.domain.usecase.user


import com.yongjincompany.domain.param.PostUserSignInParam
import com.yongjincompany.domain.repository.UserRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUserSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PostUserSignInParam, Unit>() {

    override suspend fun execute(data: PostUserSignInParam): Unit =
        userRepository.postUserSignIn(data)
}