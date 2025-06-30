package soft.divan.data_ui.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.data_ui.R
import soft.divan.moodtracker.core.domain.model.HobbyCategory

data class HobbyCategory(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int,
)

fun HobbyCategory.mapToPresenter(): soft.divan.data_ui.data.HobbyCategory =
    when (this) {
        HobbyCategory.READING -> HobbyCategory(R.string.hobby_reading_emoji, R.string.hobby_reading)
        HobbyCategory.SPORTS -> HobbyCategory(R.string.hobby_sports_emoji, R.string.hobby_sports)
        HobbyCategory.MUSIC -> HobbyCategory(R.string.hobby_music_emoji, R.string.hobby_music)
        HobbyCategory.ART -> HobbyCategory(R.string.hobby_art_emoji, R.string.hobby_art)
        HobbyCategory.WALKING -> HobbyCategory(R.string.hobby_walking_emoji, R.string.hobby_walking)
        HobbyCategory.GAMES -> HobbyCategory(R.string.hobby_games_emoji, R.string.hobby_games)
        HobbyCategory.OTHER -> HobbyCategory(R.string.hobby_other_emoji, R.string.hobby_other)
    }