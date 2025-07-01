package soft.divan.moodtracker.core.network.network.dto

import com.google.gson.annotations.SerializedName

data class ChatCompletionResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("choices")
    val choices: List<ChoiceDto>,
    @SerializedName("usage")
    val usage: UsageDto?
)