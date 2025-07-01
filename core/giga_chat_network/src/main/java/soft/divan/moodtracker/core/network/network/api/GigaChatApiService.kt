package soft.divan.moodtracker.core.network.network.api

import retrofit2.http.Body
import retrofit2.http.POST
import soft.divan.moodtracker.core.network.network.dto.ChatCompletionRequestDto
import soft.divan.moodtracker.core.network.network.dto.ChatCompletionResponseDto

interface GigaChatApiService {

    @POST("api/v1/chat/completions")
    suspend fun getChatCompletion(
        @Body request: ChatCompletionRequestDto
    ): ChatCompletionResponseDto
}
