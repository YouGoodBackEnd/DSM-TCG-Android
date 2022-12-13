package com.yongjincompany.dsmtcg.viewmodel.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.user.LogOutUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogOutViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun logOut() {
        viewModelScope.launch {
            kotlin.runCatching {
                logOutUseCase.execute(Unit)
            }.onSuccess {
                event(Event.LogOut)
            }.onFailure {
                event(Event.LogOutFailed)
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        object LogOut : Event()
        object LogOutFailed : Event()
    }
}

