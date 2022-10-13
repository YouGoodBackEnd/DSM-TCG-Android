package com.yongjincompany.domain.entity.rooms

data class FetchRoomEntity(
    val roomList: List<Room>
) {
    data class Room(
        val roomId: Int,
        val roomName: String,
        val profileImage: String,
        val userName: String,
    )
}