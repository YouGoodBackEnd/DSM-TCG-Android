package com.yongjincompany.domain.usecase.rank

import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import com.yongjincompany.domain.repository.RankRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllRankUseCase @Inject constructor(
    private val rankRepository: RankRepository,
) : UseCase<Unit, Flow<FetchRankEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchRankEntity> =
        rankRepository.fetchAllRank()
}