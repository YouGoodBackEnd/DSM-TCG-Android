package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun fetchMyCard(): Flow<FetchMyCardEntity>
}