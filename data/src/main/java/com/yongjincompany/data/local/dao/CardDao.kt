package com.yongjincompany.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongjincompany.data.local.entity.card.FetchMyCardRoomEntity

@Dao
interface CardDao {
    @Query("SELECT * FROM myCard")
    suspend fun fetchMyCard(): FetchMyCardRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyCard(fetchMyCardRoomEntity: FetchMyCardRoomEntity)
}