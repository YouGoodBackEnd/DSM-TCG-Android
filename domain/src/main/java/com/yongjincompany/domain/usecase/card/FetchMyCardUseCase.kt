package com.yongjincompany.domain.usecase.card

import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.repository.CardRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMyCardUseCase @Inject constructor(
    private val cardRepository: CardRepository,
) : UseCase<Unit, Flow<FetchMyCardEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchMyCardEntity> =
        cardRepository.fetchMyCard()
}