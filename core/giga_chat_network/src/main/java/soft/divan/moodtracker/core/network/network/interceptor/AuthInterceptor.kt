package soft.divan.moodtracker.core.network.network.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(
    private val tokenProvider: GigaChatTokenProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = runBlocking { tokenProvider.getToken() }
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}
