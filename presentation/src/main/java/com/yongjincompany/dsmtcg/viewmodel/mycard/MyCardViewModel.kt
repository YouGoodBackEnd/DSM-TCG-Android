package com.yongjincompany.dsmtcg.viewmodel.mycard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.exception.BadRequestException
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.card.FetchMyCardUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCardViewModel @Inject constructor(
    private val fetchMyCardUseCase: FetchMyCardUseCase,
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchMyCardValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchMyCardUseCase.execute(Unit).collect {
                    event(Event.FetchMyCard(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었습니다. 다시 로그인해주세요."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun FetchMyCardEntity.CardCount.toData() =
        FetchMyCardEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            ssgradeCardCount = ssgradeCardCount
        )

    private fun FetchMyCardEntity.Card.toData() =
        FetchMyCardEntity.Card(
            cardId = cardId,
            cardImageUrl = cardImageUrl,
            description = description,
            count = count,
            grade = grade,
            name = name
        )

    private fun FetchMyCardEntity.toData() =
        FetchMyCardEntity(
            cardCount = cardCount.toData(),
            totalCount = totalCount,
            cardList = cardList.map { it.toData() }
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchMyCard(val fetchMyCardEntity: FetchMyCardEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}