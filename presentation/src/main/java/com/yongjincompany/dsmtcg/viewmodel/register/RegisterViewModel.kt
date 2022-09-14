package com.yongjincompany.dsmtcg.viewmodel.register

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.name
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.exception.BadRequestException
import com.yongjincompany.domain.exception.ConflictException
import com.yongjincompany.domain.param.user.PostUserRegisterParam
import com.yongjincompany.domain.usecase.user.PostUserRegisterUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val postUserRegisterUseCase: PostUserRegisterUseCase,
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun register(accountId: String, password: String, name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                postUserRegisterUseCase.execute(PostUserRegisterParam(
                    accountId = accountId,
                    password = password,
                    name = name,
                ))
            }.onSuccess {
                event((Event.SuccessRegister(true)))
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.BadRequest)
                    is ConflictException -> event(Event.Conflict)
                    else -> event(Event.UnKnown)
                }
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SuccessRegister(var state: Boolean = false) : Event()
        object BadRequest : Event()
        object Conflict : Event()
        object UnKnown : Event()
        data class ErrorMessage(val message: String) : Event()
    }

}