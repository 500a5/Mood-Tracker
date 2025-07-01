package soft.divan.moodtracker.core.network.network.interceptor

import android.util.Base64
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import soft.divan.moodtracker.core.network.BuildConfig
import soft.divan.moodtracker.core.network.network.api.GigaChatAuthService
import javax.inject.Inject

class GigaChatTokenProvider @Inject constructor(
    private val authService: GigaChatAuthService
) {
    private var token: String? = null
    private val mutex = Mutex()

    suspend fun getToken(): String {
        if (token != null) return token!!

        return mutex.withLock {
            if (token == null) {
                val credentials = "${BuildConfig.CLIENT_ID}:${BuildConfig.CLIENT_SECRET}"
                val encoded = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
                token = authService.getAccessToken("Basic $encoded").accessToken
            }
            token!!
        }
    }
}
