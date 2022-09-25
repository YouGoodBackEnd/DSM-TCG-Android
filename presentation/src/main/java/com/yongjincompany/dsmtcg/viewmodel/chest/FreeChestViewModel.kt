package com.yongjincompany.dsmtcg.viewmodel.chest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.entity.chests.*
import com.yongjincompany.domain.exception.ConflictException
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.chest.*
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FreeChestViewModel @Inject constructor(
    private val openFreeChestUseCase: OpenFreeChestUseCase,
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun openFreeChestValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                openFreeChestUseCase.execute(Unit).collect {
                    event(Event.OpenFreeChest(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is ConflictException -> event(Event.ErrorMessage("상자 오픈 시간이 아닙니다"))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었습니다. 다시 로그인해주세요."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun FreeChestOpenEntity.toData() =
        FreeChestOpenEntity(
            cardList = cardList.map { it.toData() },
            coin = coin,
            diamond = diamond
        )

    private fun FreeChestOpenEntity.Card.toData() =
        FreeChestOpenEntity.Card(
            cardImageUrl = cardImageUrl,
            cardName = cardName,
            description = description,
            grade = grade,
            id = id
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class OpenFreeChest(val freeChestOpenEntity: FreeChestOpenEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}


