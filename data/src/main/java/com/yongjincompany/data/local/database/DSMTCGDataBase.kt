package com.yongjincompany.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yongjincompany.data.local.converter.CardListTypeConverter
import com.yongjincompany.data.local.dao.CardDao
import com.yongjincompany.data.local.dao.UserDao
import com.yongjincompany.data.local.entity.user.FetchMyInfoRoomEntity

@Database(
    entities = [
        FetchMyInfoRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(
    value = [
        CardListTypeConverter::class
    ]

)

abstract class DSMTCGDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cardDao(): CardDao
}