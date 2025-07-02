package soft.divan.data_ui.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.data_ui.R
import soft.divan.moodtracker.core.domain.model.DayMoodRating

data class DayMoodRating(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int
)

fun DayMoodRating.mapToPresenter(): soft.divan.data_ui.data.DayMoodRating =
    when (this) {
        DayMoodRating.AMAZING -> DayMoodRating(R.string.mood_amazing_emoji, R.string.mood_amazing)
        DayMoodRating.GOOD -> DayMoodRating(R.string.mood_good_emoji, R.string.mood_good)
        DayMoodRating.NORMAL -> DayMoodRating(R.string.mood_normal_emoji, R.string.mood_normal)
        DayMoodRating.BAD -> DayMoodRating(R.string.mood_bad_emoji, R.string.mood_bad)
        DayMoodRating.AWFUL -> DayMoodRating(R.string.mood_awful_emoji, R.string.mood_awful)
    }

fun DayMoodRating.toInt(): Int = when (this) {
    DayMoodRating.AWFUL -> 1
    DayMoodRating.BAD -> 2
    DayMoodRating.NORMAL -> 3
    DayMoodRating.GOOD -> 4
    DayMoodRating.AMAZING -> 5
}
