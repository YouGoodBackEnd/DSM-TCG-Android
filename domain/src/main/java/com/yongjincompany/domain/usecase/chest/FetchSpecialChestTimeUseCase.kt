package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSpecialChestTimeUseCase @Inject constructor(
    private val chestRepository: ChestRepository
): UseCase<Unit, Flow<FetchSpecialChestTimeEntity>>() {
    override suspend fun execute(data: Unit): Flow<FetchSpecialChestTimeEntity> =
        chestRepository.fetchSpecialChestTime()
}