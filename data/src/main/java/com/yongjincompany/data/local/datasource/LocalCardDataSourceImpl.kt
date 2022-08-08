package com.yongjincompany.data.local.datasource

import com.yongjincompany.data.local.dao.CardDao
import com.yongjincompany.data.local.entity.card.toDbEntity
import com.yongjincompany.data.local.entity.card.toEntity
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import javax.inject.Inject

class LocalCardDataSourceImpl @Inject constructor(
    private val cardDao: CardDao,
) : LocalCardDataSource {
    override suspend fun fetchMyCard(): FetchMyCardEntity =
        cardDao.fetchMyCard().toEntity()

    override suspend fun insertMyCard(fetchMyCardEntity: FetchMyCardEntity) {
        cardDao.insertMyCard(fetchMyCardEntity.toDbEntity())
    }
}