package com.yongjincompany.data.remote.response.rooms

import com.google.gson.annotations.SerializedName
import com.yongjincompany.domain.entity.rooms.FetchRoomEntity

data class FetchRoomResponse(
    @SerializedName("room_list") val roomList: List<Room>
) {
    data class Room(
        @SerializedName("room_id") val roomId: Int,
        @SerializedName("room_name") val roomName: String,
        @SerializedName("profile_image") val profileImage: String,
        @SerializedName("username") val userName: String,
    )

    fun Room.toEntity() =
        FetchRoomEntity.Room(
            roomId = roomId,
            roomName = roomName,
            profileImage = profileImage,
            userName = userName,
        )
}

fun FetchRoomResponse.toEntity() =
    FetchRoomEntity(
        roomList = roomList.map { it.toEntity() }
    )