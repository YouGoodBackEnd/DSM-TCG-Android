package com.yongjincompany.dsmtcg.di

import com.yongjincompany.data.repository.CardRepositoryImpl
import com.yongjincompany.data.repository.ChestRepositoryImpl
import com.yongjincompany.data.repository.RankRepositoryImpl
import com.yongjincompany.data.repository.UserRepositoryImpl
import com.yongjincompany.domain.repository.CardRepository
import com.yongjincompany.domain.repository.ChestRepository
import com.yongjincompany.domain.repository.RankRepository
import com.yongjincompany.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /*@Singleton
    @Binds
    abstract fun providesSocketRepository(
        socketRepositoryImpl: SocketRepositoryImpl
    ): SocketRepository*/

    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun provideCardRepository(
        cardRepositoryImpl: CardRepositoryImpl,
    ): CardRepository

    @Binds
    abstract fun provideChestRepository(
        chestRepositoryImpl: ChestRepositoryImpl,
    ): ChestRepository

    @Binds
    abstract fun provideRankRepository(
        rankRepositoryImpl: RankRepositoryImpl
    ): RankRepository
}