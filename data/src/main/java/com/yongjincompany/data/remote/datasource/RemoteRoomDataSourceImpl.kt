package com.yongjincompany.data.remote.datasource

import com.yongjincompany.data.remote.api.RoomApi
import com.yongjincompany.data.remote.response.rooms.FetchRoomResponse
import com.yongjincompany.data.remote.response.rooms.toEntity
import com.yongjincompany.data.util.HttpHandler
import com.yongjincompany.domain.entity.rooms.FetchRoomEntity
import javax.inject.Inject

class RemoteRoomDataSourceImpl @Inject constructor(
    private val roomApi: RoomApi
): RemoteRoomDataSource {
    override suspend fun fetchRooms(): FetchRoomEntity =
        HttpHandler<FetchRoomResponse>()
            .httpRequest { roomApi.getRooms() }
            .sendRequest().toEntity()
}