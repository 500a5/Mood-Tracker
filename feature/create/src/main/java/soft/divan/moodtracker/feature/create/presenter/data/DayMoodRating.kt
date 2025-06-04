package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.model.DayMoodRating
import soft.divan.moodtracker.feature.create.R

data class DayMoodRating(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int
)

fun DayMoodRating.mapToPresenter(): soft.divan.moodtracker.feature.create.presenter.data.DayMoodRating =
    when (this) {
        DayMoodRating.AMAZING -> DayMoodRating(R.string.mood_amazing_emoji, R.string.mood_amazing)
        DayMoodRating.GOOD -> DayMoodRating(R.string.mood_good_emoji, R.string.mood_good)
        DayMoodRating.NORMAL -> DayMoodRating(R.string.mood_normal_emoji, R.string.mood_normal)
        DayMoodRating.BAD -> DayMoodRating(R.string.mood_bad_emoji, R.string.mood_bad)
        DayMoodRating.AWFUL -> DayMoodRating(R.string.mood_awful_emoji, R.string.mood_awful)
    }