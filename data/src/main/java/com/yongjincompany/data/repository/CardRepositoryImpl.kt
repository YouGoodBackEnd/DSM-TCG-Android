package com.yongjincompany.data.repository

import com.yongjincompany.data.local.datasource.LocalCardDataSource
import com.yongjincompany.data.remote.datasource.RemoteCardDataSource
import com.yongjincompany.data.remote.datasource.RemoteCardDataSourceImpl
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.entity.cards.FreeCardEntity
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val remoteCardDataSource: RemoteCardDataSource,
    private val localCardDataSource: LocalCardDataSource
): CardRepository {
    override suspend fun drawFreeCard(): Flow<FreeCardEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMyCard(): Flow<FetchMyCardEntity> =
        OfflineCacheUtil<FetchMyCardEntity>()
            .remoteData { remoteCardDataSource.fetchMyCard() }
            .localData { localCardDataSource.fetchMyCard() }
            .doOnNeedRefresh { localCardDataSource.insertMyCard(it) }
            .createFlow()

}