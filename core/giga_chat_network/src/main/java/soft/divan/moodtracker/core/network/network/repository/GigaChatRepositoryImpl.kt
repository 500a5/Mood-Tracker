package soft.divan.moodtracker.core.network.network.repository

import android.util.Log
import soft.divan.moodtracker.core.domain.model.ChatMessage
import soft.divan.moodtracker.core.domain.repository.GigaChatRepository
import soft.divan.moodtracker.core.network.network.api.GigaChatApiService
import soft.divan.moodtracker.core.network.network.dto.ChatCompletionRequestDto
import soft.divan.moodtracker.core.network.network.dto.MessageDto
import javax.inject.Inject

class GigaChatRepositoryImpl @Inject constructor(
    private val api: GigaChatApiService
) : GigaChatRepository {

    override suspend fun analyzeMessages(messages: List<ChatMessage>): String {
        val dtoMessages = messages.map { MessageDto(it.role, it.content) }
        val request = ChatCompletionRequestDto(messages = dtoMessages)
        val response = api.getChatCompletion(request)
        Log.d("GigaChatRepositoryImpl", "analyzeMessages: $response")
        return response.choices.firstOrNull()?.message?.content ?: "Нет ответа"
    }
}