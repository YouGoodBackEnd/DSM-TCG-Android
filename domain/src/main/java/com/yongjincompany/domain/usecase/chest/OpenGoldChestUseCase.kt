package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.GoldChestOpenEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenGoldChestUseCase @Inject constructor(
    private val chestRepository: ChestRepository
): UseCase<Unit, Flow<GoldChestOpenEntity>>() {
    override suspend fun execute(data: Unit): Flow<GoldChestOpenEntity> =
        chestRepository.openGoldChest()
}