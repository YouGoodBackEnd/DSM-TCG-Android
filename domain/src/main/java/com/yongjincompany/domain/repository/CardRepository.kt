package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.cards.FreeCardEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun drawFreeCard(): Flow<FreeCardEntity>
}