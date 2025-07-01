package soft.divan.moodtracker.core.network.network.dto

import com.google.gson.annotations.SerializedName

data class AccessTokenResponseDto(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("token_type") val tokenType: String
)
