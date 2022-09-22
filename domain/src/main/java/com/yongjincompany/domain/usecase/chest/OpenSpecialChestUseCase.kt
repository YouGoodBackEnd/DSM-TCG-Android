package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenSpecialChestUseCase @Inject constructor(
    private val chestRepository: ChestRepository,
) : UseCase<Unit, Flow<SpecialChestOpenEntity>>() {
    override suspend fun execute(data: Unit): Flow<SpecialChestOpenEntity> =
        chestRepository.openSpecialChest()
}