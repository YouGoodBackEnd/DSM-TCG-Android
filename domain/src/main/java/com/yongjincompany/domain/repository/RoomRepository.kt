package com.yongjincompany.domain.repository

import com.yongjincompany.domain.entity.rooms.FetchRoomEntity
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun fetchRooms(): Flow<FetchRoomEntity>
}