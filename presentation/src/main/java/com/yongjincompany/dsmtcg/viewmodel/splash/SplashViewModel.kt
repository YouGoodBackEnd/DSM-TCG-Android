package com.yongjincompany.dsmtcg.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.usecase.user.AutoLoginUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val autoLoginUseCase: AutoLoginUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun autoLogin() = viewModelScope.launch {
        kotlin.runCatching {
            autoLoginUseCase.execute(Unit)
        }.onSuccess {
            emitEvent(Event.AutoLoginSuccess)
        }.onFailure {
            emitEvent(Event.NeedLogin)
        }

    }

    private suspend fun emitEvent(event: Event) {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object AutoLoginSuccess : Event()
        object NeedLogin : Event()
    }
}