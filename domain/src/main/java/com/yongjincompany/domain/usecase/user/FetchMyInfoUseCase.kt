package com.yongjincompany.domain.usecase.user

import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.repository.UserRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<FetchMyInfoEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchMyInfoEntity> =
        userRepository.fetchMyInfo()
}