package com.yongjincompany.dsmtcg.di

import com.yongjincompany.data.remote.api.*
import com.yongjincompany.data.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    private const val BASE_URL = "http://52.5.10.3:8080"
    private const val SOCKET_BASE_URL = "http://"

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()

  /*  @Provides
    fun provideOptions(
        okHttpClient: OkHttpClient,
    ): IO.Options {
        val options = IO.Options()
        options.callFactory = okHttpClient
        options.webSocketFactory = okHttpClient
        options.transports = arrayOf(WebSocket.NAME)
        return options
    }

    @Provides
    fun provideSocket(
        options: IO.Options,
    ): Socket {
        return IO.socket(SOCKET_BASE_URL, options)
    }*/

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    fun provideImageApi(retrofit: Retrofit): ImagesApi =
        retrofit.create(ImagesApi::class.java)

    @Provides
    fun provideCardApi(retrofit: Retrofit): CardApi =
        retrofit.create(CardApi::class.java)

    @Provides
    fun provideChestApi(retrofit: Retrofit): ChestApi =
        retrofit.create(ChestApi::class.java)

    @Provides
    fun provideRankApi(retrofit: Retrofit): RankApi =
        retrofit.create(RankApi::class.java)
}