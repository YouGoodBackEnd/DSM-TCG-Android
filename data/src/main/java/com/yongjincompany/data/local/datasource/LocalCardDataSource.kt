package com.yongjincompany.data.local.datasource

import com.yongjincompany.domain.entity.cards.FetchMyCardEntity

interface LocalCardDataSource {
    suspend fun fetchMyCard(): FetchMyCardEntity
    suspend fun insertMyCard(fetchMyCardEntity: FetchMyCardEntity)
}