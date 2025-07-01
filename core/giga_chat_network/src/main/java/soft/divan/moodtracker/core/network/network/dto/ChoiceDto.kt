package soft.divan.moodtracker.core.network.network.dto

import com.google.gson.annotations.SerializedName

data class ChoiceDto(
    @SerializedName("message")
    val message: MessageDto,
    @SerializedName("finish_reason")
    val finishReason: String,
    @SerializedName("index")
    val index: Int
)