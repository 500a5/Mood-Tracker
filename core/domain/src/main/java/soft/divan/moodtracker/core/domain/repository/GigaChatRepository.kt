package soft.divan.moodtracker.core.domain.repository

import soft.divan.moodtracker.core.domain.model.ChatMessage

interface GigaChatRepository {
    suspend fun analyzeMessages(messages: List<ChatMessage>): String
}