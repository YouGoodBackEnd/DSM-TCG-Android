package com.yongjincompany.dsmtcg.viewmodel.chest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.entity.chests.SilverChestOpenEntity
import com.yongjincompany.domain.exception.ConflictException
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.param.chest.SilverChestOpenParam
import com.yongjincompany.domain.usecase.chest.OpenSilverChestUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SilverChestViewModel @Inject constructor(
    private val openSilverChestUseCase: OpenSilverChestUseCase,
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val parameter = SilverChestOpenParam(
        price = 3000
    )

    fun openSilverChestValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                openSilverChestUseCase.execute(parameter).collect {
                    event(Event.OpenSilverChest(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었습니다. 다시 로그인해주세요."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun SilverChestOpenEntity.toData() =
        SilverChestOpenEntity(
            cardList = cardList.map { it.toData() },
            coin = coin,
            diamond = diamond
        )

    private fun SilverChestOpenEntity.Card.toData() =
        SilverChestOpenEntity.Card(
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
        data class OpenSilverChest(val silverChestOpenEntity: SilverChestOpenEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}