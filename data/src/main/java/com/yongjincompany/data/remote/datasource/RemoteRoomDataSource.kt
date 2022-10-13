package com.yongjincompany.data.remote.datasource

import com.yongjincompany.domain.entity.rooms.FetchRoomEntity
import javax.inject.Inject

interface RemoteRoomDataSource {
    suspend fun fetchRooms(): FetchRoomEntity
}