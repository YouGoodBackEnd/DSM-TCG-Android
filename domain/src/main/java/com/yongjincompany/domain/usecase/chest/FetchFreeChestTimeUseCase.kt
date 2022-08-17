package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFreeChestTimeUseCase @Inject constructor(
    private val chestRepository: ChestRepository
): UseCase<Unit, Flow<FetchFreeChestTimeEntity>>() {
    override suspend fun execute(data: Unit): Flow<FetchFreeChestTimeEntity> =
        chestRepository.fetchFreeChestTime()
}