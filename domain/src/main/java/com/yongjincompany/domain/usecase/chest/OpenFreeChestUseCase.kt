package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.FreeChestOpenEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenFreeChestUseCase @Inject constructor(
    private val chestRepository: ChestRepository,
) : UseCase<Unit, Flow<FreeChestOpenEntity>>() {
    override suspend fun execute(data: Unit): Flow<FreeChestOpenEntity> =
        chestRepository.openFreeChest()
}