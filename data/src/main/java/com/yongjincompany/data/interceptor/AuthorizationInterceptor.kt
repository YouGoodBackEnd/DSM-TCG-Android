package com.yongjincompany.data.interceptor

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.yongjincompany.data.local.storage.AuthDataStorage
import com.yongjincompany.domain.exception.NeedLoginException
import okhttp3.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val authDataStorage: AuthDataStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        //accessToken이 필요없는 것들 집합
        val ignorePath = listOf(
            "/users/auth"
        )
        //accesstoken없이 그냥 동작하게 하는 코드 chain.proceed(서버에게 통신을 하고 응답을 받아올 수 있게한다.)
        if (ignorePath.contains(path)) return chain.proceed(request)
        if (path == "/users" && request.method() == "POST") return chain.proceed(request)
        //if (path.contains("/users/accounts/")) return chain.proceed(request)

        //저장해놓은 ExpiredAt받아오기
        val expiredAt = authDataStorage.fetchExpiredAt()
        //현재 Zone의 LocalDateTime
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        //만약 저장해놓은 ExpiredAt이 현재시간 전이라면
        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val refreshToken = authDataStorage.fetchRefreshToken()

            //put 해주는 코드 (토큰 재발급api)
            val tokenRefreshRequest = Request.Builder()
                .url("http://52.5.10.3:8080/users/auth")
                .put(RequestBody.create(MediaType.parse("application/json"), ""))
                .addHeader("X-Refresh-Token", refreshToken)
                .build()
            //response
            val response = client.newCall(tokenRefreshRequest).execute()
            //만약 토큰 재발급이 됬다면.
            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body()!!.toString(),
                    TokenRefreshResponse::class.java
                )
                //AccessToken, RefreshToken, ExpiredAt다 토큰 재발급 Response를 넣어준다.
                authDataStorage.setAccessToken(token.accessToken)
                authDataStorage.setRefreshToken(token.refreshToken)
                authDataStorage.setExpiredAt(token.expiredAt)
            } else throw NeedLoginException() //아닐 시 NeedLoginException
        }

        //AccessToken 받아오기
        val accessToken = authDataStorage.fetchAccessToken()
        //서버에 보내는 request에 addHeadaer(Authorization Bearer aceessToken을 build해서 반환해준다)
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken"
            ).build()
        )
    }

    data class TokenRefreshResponse(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("refresh_token") val refreshToken: String,
        @SerializedName("expired_at") val expiredAt: String,
    )
}