package com.yongjincompany.domain.usecase.chest

import com.yongjincompany.domain.entity.chests.SilverChestOpenEntity
import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OpenSilverChestUseCase @Inject constructor(
    private val chestRepository: ChestRepository,
) : UseCase<Unit, Flow<SilverChestOpenEntity>>() {
    override suspend fun execute(data: Unit): Flow<SilverChestOpenEntity> =
        chestRepository.openSilverChest()
}