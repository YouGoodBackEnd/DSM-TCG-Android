package com.yongjincompany.dsmtcg.viewmodel.chest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yongjincompany.domain.entity.chests.*
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.domain.exception.BadRequestException
import com.yongjincompany.domain.exception.ConflictException
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.chest.*
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChestViewModel @Inject constructor(
    private val openFreeChestUseCase: OpenFreeChestUseCase,
    private val openSpecialChestUseCase: OpenSpecialChestUseCase,
    private val openSilverChestUseCase: OpenSilverChestUseCase,
    private val openGoldChestUseCase: OpenGoldChestUseCase,
    private val openLegendChestUseCase: OpenLegendChestUseCase,
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

    fun openSpecialChestValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                openSpecialChestUseCase.execute(Unit).collect {
                    event(Event.OpenSpecialChest(it.toData()))
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

    fun openSilverChestValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                openSilverChestUseCase.execute(Unit).collect {
                    event(Event.OpenSilverChest(it.toData()))
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

    fun openGoldChestValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                openGoldChestUseCase.execute(Unit).collect {
                    event(Event.OpenGoldChest(it.toData()))
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

    fun openLegendChestValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                openLegendChestUseCase.execute(Unit).collect {
                    event(Event.OpenLegendChest(it.toData()))
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


    private fun LegendChestOpenEntity.toData() =
        LegendChestOpenEntity(
            cardList = cardList.map { it.toData() },
            coin = coin,
            diamond = diamond
        )

    private fun LegendChestOpenEntity.Card.toData() =
        LegendChestOpenEntity.Card(
            cardImageUrl = cardImageUrl,
            cardName = cardName,
            description = description,
            grade = grade,
            id = id
        )

    private fun GoldChestOpenEntity.toData() =
        GoldChestOpenEntity(
            cardList = cardList.map { it.toData() },
            coin = coin,
            diamond = diamond
        )

    private fun GoldChestOpenEntity.Card.toData() =
        GoldChestOpenEntity.Card(
            cardImageUrl = cardImageUrl,
            cardName = cardName,
            description = description,
            grade = grade,
            id = id
        )

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

    private fun SpecialChestOpenEntity.toData() =
        SpecialChestOpenEntity(
            cardList = cardList.map { it.toData() },
            coin = coin,
            diamond = diamond
        )

    private fun SpecialChestOpenEntity.Card.toData() =
        SpecialChestOpenEntity.Card(
            cardImageUrl = cardImageUrl,
            cardName = cardName,
            description = description,
            grade = grade,
            id = id
        )


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
        data class OpenSpecialChest(val specialChestOpenEntity: SpecialChestOpenEntity) : Event()
        data class OpenFreeChest(val freeChestOpenEntity: FreeChestOpenEntity) : Event()
        data class OpenSilverChest(val silverChestOpenEntity: SilverChestOpenEntity) : Event()
        data class OpenGoldChest(val goldChestOpenEntity: GoldChestOpenEntity) : Event()
        data class OpenLegendChest(val legendChestOpenEntity: LegendChestOpenEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}


