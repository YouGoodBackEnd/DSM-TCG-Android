package com.yongjincompany.data.remote.datasource

import com.yongjincompany.domain.entity.cards.FetchMyCardEntity

interface RemoteCardDataSource {
    suspend fun fetchMyCard(): FetchMyCardEntity
}