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
        val ignorePath = listOf(
            "/users/auth"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)
        if (path == "/users" && request.method() == "POST") return chain.proceed(request)
        //if (path.contains("/users/accounts/")) return chain.proceed(request)

        val expiredAt = authDataStorage.fetchExpiredAt()
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val refreshToken = authDataStorage.fetchRefreshToken()

            val tokenRefreshRequest = Request.Builder()
                .url("http://52.5.10.3:8080/users/auth")
                .put(RequestBody.create(MediaType.parse("application/json"), ""))
                .addHeader("X-Refresh-Token", refreshToken)
                .build()
            val response = client.newCall(tokenRefreshRequest).execute()

            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body()!!.toString(),
                    TokenRefreshResponse::class.java
                )
                authDataStorage.setAccessToken(token.accessToken)
                authDataStorage.setRefreshToken(token.refreshToken)
                authDataStorage.setExpiredAt(token.expiredAt)
            } else throw NeedLoginException()
        }

        val accessToken = authDataStorage.fetchAccessToken()
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