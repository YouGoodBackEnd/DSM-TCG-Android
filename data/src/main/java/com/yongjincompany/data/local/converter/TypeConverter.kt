package com.yongjincompany.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.yongjincompany.data.local.entity.card.FetchMyCardRoomEntity

@ProvidedTypeConverter
class CardListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<FetchMyCardRoomEntity.Card>? {
        val listType = Types.newParameterizedType(
            List::class.java,
            FetchMyCardRoomEntity.Card::class.java
        )
        val adapter: JsonAdapter<List<FetchMyCardRoomEntity.Card>> =
            moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<FetchMyCardRoomEntity.Card>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            FetchMyCardRoomEntity.Card::class.java
        )
        val adapter: JsonAdapter<List<FetchMyCardRoomEntity.Card>> =
            moshi.adapter(listType)
        return adapter.toJson(type)
    }

}