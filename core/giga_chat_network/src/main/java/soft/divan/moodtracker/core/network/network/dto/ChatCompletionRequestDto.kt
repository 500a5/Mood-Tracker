package soft.divan.moodtracker.core.network.network.dto

import com.google.gson.annotations.SerializedName

data class ChatCompletionRequestDto(
    @SerializedName("model")
    val model: String = "GigaChat:latest",
    @SerializedName("messages")
    val messages: List<MessageDto>,
    @SerializedName("temperature")
    val temperature: Float = 1.0f,
    @SerializedName("top_p")
    val topP: Float = 0.9f,
    @SerializedName("n")
    val n: Int = 1,
    @SerializedName("stream")
    val stream: Boolean = false
)
