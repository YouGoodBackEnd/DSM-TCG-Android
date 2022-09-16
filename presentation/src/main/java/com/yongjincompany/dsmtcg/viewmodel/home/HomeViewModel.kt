package com.yongjincompany.dsmtcg.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.exception.BadRequestException
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.user.FetchMyInfoUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMyInfoUseCase: FetchMyInfoUseCase,

    ) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchMyInfoValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchMyInfoUseCase.execute(Unit).collect {
                    event(Event.FetchMyInfo(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.ErrorMessage("잘못된 요청입니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었습니다. 다시 로그인해주세요."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun FetchMyInfoEntity.toData() =
        FetchMyInfoEntity(
            name = name,
            profileImageUrl = profileImageUrl,
            rank = rank,
            userId = userId,
            cardCount = cardCount.toData()
        )

    private fun FetchMyInfoEntity.CardCount.toData() =
        FetchMyInfoEntity.CardCount(
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
        data class FetchMyInfo(val fetchMyInfoEntity: FetchMyInfoEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}
