package soft.divan.moodtracker.core.network.network.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import soft.divan.moodtracker.core.network.network.dto.AccessTokenResponseDto
import java.util.UUID

interface GigaChatAuthService {
    @FormUrlEncoded
    @POST("api/v2/oauth")
    suspend fun getAccessToken(
        @Header("Authorization") authHeader: String,
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded",
        @Header("Accept") accept: String = "application/json",
        @Header("RqUID") rqUid: String = UUID.randomUUID().toString(),
        @Field("scope") scope: String = "GIGACHAT_API_PERS"

    ): AccessTokenResponseDto
}

