package com.yongjincompany.domain.usecase.room

import com.yongjincompany.domain.entity.rooms.FetchRoomEntity
import com.yongjincompany.domain.repository.RoomRepository
import com.yongjincompany.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) : UseCase<Unit, Flow<FetchRoomEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchRoomEntity> =
        roomRepository.fetchRooms()
}