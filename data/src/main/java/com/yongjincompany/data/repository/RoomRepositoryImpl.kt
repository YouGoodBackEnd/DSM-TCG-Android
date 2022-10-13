package com.yongjincompany.data.repository

import com.yongjincompany.data.remote.datasource.RemoteRoomDataSource
import com.yongjincompany.data.util.OfflineCacheUtil
import com.yongjincompany.domain.entity.rooms.FetchRoomEntity
import com.yongjincompany.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val remoteRoomDataSource: RemoteRoomDataSource
) : RoomRepository {
    override suspend fun fetchRooms(): Flow<FetchRoomEntity> =
        OfflineCacheUtil<FetchRoomEntity>()
            .remoteData { remoteRoomDataSource.fetchRooms() }
            .createRemoteFlow()
}