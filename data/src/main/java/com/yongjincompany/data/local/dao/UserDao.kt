package com.yongjincompany.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongjincompany.data.local.entity.user.FetchMyInfoRoomEntity

interface UserDao {
    @Query("SELECT * FROM myInfo")
    suspend fun fetchMyInfo() : FetchMyInfoRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyInfo(fetchMyInfoRoomEntity: FetchMyInfoRoomEntity)
}