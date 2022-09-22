package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.LegendChestOpenEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenLegendChestUseCase @Inject constructor(
    private val chestRepository: ChestRepository
): UseCase<Unit, Flow<LegendChestOpenEntity>>() {
    override suspend fun execute(data: Unit): Flow<LegendChestOpenEntity> =
        chestRepository.openLegendChest()
}