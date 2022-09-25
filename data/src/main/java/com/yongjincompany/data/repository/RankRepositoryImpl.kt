package com.yongjincompany.data.repository

import com.yongjincompany.data.remote.datasource.RemoteRankDataSource
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import com.yongjincompany.domain.repository.RankRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val remoteRankDataSource: RemoteRankDataSource
): RankRepository {
    override suspend fun fetchAllRank(): Flow<FetchRankEntity> =
        OfflineCacheUtil<FetchRankEntity>()
            .remoteData { remoteRankDataSource.fetchAllRank() }
            .createRemoteFlow()
}