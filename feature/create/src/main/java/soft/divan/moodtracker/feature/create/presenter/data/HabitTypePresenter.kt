package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.model.HabitType
import soft.divan.moodtracker.feature.create.R

data class HabitTypePresenter(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int
)

fun HabitType.mapToPresenter(): HabitTypePresenter = when (this) {
    HabitType.ALCOHOL -> HabitTypePresenter(R.string.habit_alcohol_emoji, R.string.habit_alcohol)
    HabitType.SMOKING -> HabitTypePresenter(R.string.habit_smoking_emoji, R.string.habit_smoking)
    HabitType.CAFFEINE -> HabitTypePresenter(R.string.habit_caffeine_emoji, R.string.habit_caffeine)
    HabitType.SCREEN_TIME -> HabitTypePresenter(R.string.habit_screen_time_emoji, R.string.habit_screen_time)

    HabitType.MEDITATION -> HabitTypePresenter(
        R.string.habit_meditation_emoji,
        R.string.habit_meditation
    )

    HabitType.EXERCISE -> HabitTypePresenter(R.string.habit_exercise_emoji, R.string.habit_exercise)
}
