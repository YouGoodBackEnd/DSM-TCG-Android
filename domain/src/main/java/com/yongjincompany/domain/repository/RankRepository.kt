package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import kotlinx.coroutines.flow.Flow

interface RankRepository {
    suspend fun fetchAllRank(): Flow<FetchRankEntity>
}