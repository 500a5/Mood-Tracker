package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.core.model.EmotionCategory

data class EmotionCategory(
    val emoji: String,
    @StringRes val labelResId: Int
)


fun EmotionCategory.mapToPresenter(): soft.divan.moodtracker.feature.create.presenter.data.EmotionCategory =
    when (this) {
        EmotionCategory.HAPPY -> EmotionCategory("😊", R.string.emotion_happy)
        EmotionCategory.ANXIOUS -> EmotionCategory("😰", R.string.emotion_anxious)
        EmotionCategory.SAD -> EmotionCategory("😢", R.string.emotion_sad)
        EmotionCategory.ANGRY -> EmotionCategory("😡", R.string.emotion_angry)
        EmotionCategory.GRATEFUL -> EmotionCategory("🙏", R.string.emotion_grateful)
        EmotionCategory.CALM -> EmotionCategory("😌", R.string.emotion_calm)
        EmotionCategory.LONELY -> EmotionCategory("🥺", R.string.emotion_lonely)
        EmotionCategory.PROUD -> EmotionCategory("😎", R.string.emotion_proud)
        EmotionCategory.STRESS -> EmotionCategory("😵", R.string.emotion_stress)
        EmotionCategory.RELAXATION -> EmotionCategory("🛀", R.string.emotion_relaxation)
        EmotionCategory.PRODUCTIVITY -> EmotionCategory("⚡", R.string.emotion_productivity)
        EmotionCategory.CONDITION -> EmotionCategory("😴", R.string.emotion_condition)
        EmotionCategory.UNCERTAINTY -> EmotionCategory("🤔", R.string.emotion_uncertainty)
    }

object EmotionMapper {

    val all: List<soft.divan.moodtracker.feature.create.presenter.data.EmotionCategory> =
        EmotionCategory.values().map { it.mapToPresenter() }
}