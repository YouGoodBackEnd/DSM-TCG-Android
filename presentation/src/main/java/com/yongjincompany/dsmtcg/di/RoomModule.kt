package com.yongjincompany.dsmtcg.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yongjincompany.data.local.converter.CardListTypeConverter
import com.yongjincompany.data.local.dao.CardDao
import com.yongjincompany.data.local.dao.UserDao
import com.yongjincompany.data.local.database.DSMTCGDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideWalkHubDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi,
    ): DSMTCGDataBase = Room
        .databaseBuilder(context, DSMTCGDataBase::class.java, "DSMDataBase")
        .build()

    @Provides
    fun provideUserDao(
        dsmtcgDataBase: DSMTCGDataBase,
    ): UserDao = dsmtcgDataBase.userDao()

    @Provides
    fun provideCardDao(
        dsmtcgDataBase: DSMTCGDataBase
    ): CardDao = dsmtcgDataBase.cardDao()
}