package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.model.DayMoodRating
import soft.divan.moodtracker.feature.create.R

data class DayMoodUiModel2(
    val emoji: String,
    @StringRes val labelResId: Int
)

fun DayMoodRating.mapToPresentr(): DayMoodUiModel2 = when (this) {
    DayMoodRating.AMAZING -> DayMoodUiModel2("😍", R.string.mood_amazing)
    DayMoodRating.GOOD    -> DayMoodUiModel2("🙂", R.string.mood_good)
    DayMoodRating.NORMAL  -> DayMoodUiModel2("😐", R.string.mood_normal)
    DayMoodRating.BAD     -> DayMoodUiModel2("🙁", R.string.mood_bad)
    DayMoodRating.AWFUL   -> DayMoodUiModel2("😫", R.string.mood_awful)
}