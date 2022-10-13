package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.response.rooms.FetchRoomResponse
import retrofit2.http.GET

interface RoomApi {
    @GET("rooms")
    suspend fun getRooms(): FetchRoomResponse
}