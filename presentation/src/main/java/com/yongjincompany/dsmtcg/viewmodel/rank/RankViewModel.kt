package com.yongjincompany.dsmtcg.viewmodel.rank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.rank.FetchAllRankUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(
    private val fetchAllRankUseCase: FetchAllRankUseCase,
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchAllRankValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchAllRankUseCase.execute(Unit).collect {
                    event(Event.FetchAllRank(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었습니다. 다시 로그인해주세요."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun FetchRankEntity.toData() =
        FetchRankEntity(
            rankList = rankList.map { it.toData() }
        )

    private fun FetchRankEntity.Rank.toData() =
        FetchRankEntity.Rank(
            cardCount = cardCount.toData(),
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            accountId = accountId
        )

    private fun FetchRankEntity.CardCount.toData() =
        FetchRankEntity.CardCount(
            agradeCardCount = agradeCardCount,
            bgradeCardCount = bgradeCardCount,
            cgradeCardCount = cgradeCardCount,
            sgradeCardCount = sgradeCardCount,
            ssgradeCardCount = ssgradeCardCount
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchAllRank(val fetchRankEntity: FetchRankEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }

}