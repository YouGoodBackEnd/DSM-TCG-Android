package com.yongjincompany.dsmtcg.viewmodel.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.domain.entity.rooms.FetchRoomEntity
import com.yongjincompany.domain.exception.UnauthorizedException
import com.yongjincompany.domain.usecase.room.FetchRoomsUseCase
import com.yongjincompany.dsmtcg.util.MutableEventFlow
import com.yongjincompany.dsmtcg.util.asEventFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomViewModel @Inject constructor(
    private val fetchRoomsUseCase: FetchRoomsUseCase
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<RoomViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchRoomValue() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchRoomsUseCase.execute(Unit).collect {
                    event(RoomViewModel.Event.FetchRoom(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(RoomViewModel.Event.ErrorMessage("토큰이 만료되었습니다. 다시 로그인해주세요."))
                    else -> event(RoomViewModel.Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }

    }

    private fun FetchRoomEntity.toData() =
        FetchRoomEntity(
            roomList.map { it.toData() }
        )

    private fun FetchRoomEntity.Room.toData() =
        FetchRoomEntity.Room(
            roomId = roomId,
            roomName = roomName,
            profileImage = profileImage,
            userName = userName,
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchRoom(val fetchRoomEntity: FetchRoomEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }

}