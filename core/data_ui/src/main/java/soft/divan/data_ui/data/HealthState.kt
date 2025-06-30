package soft.divan.data_ui.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.data_ui.R
import soft.divan.moodtracker.core.domain.model.HealthState

data class HealthState(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int
)

fun HealthState.mapToPresenter(): soft.divan.data_ui.data.HealthState =
    when (this) {
        HealthState.HEALTHY -> HealthState(R.string.health_healthy_emoji, R.string.health_healthy)
        HealthState.HEADACHE -> HealthState(R.string.health_headache_emoji, R.string.health_headache)
        HealthState.STOMACHACHE -> HealthState(R.string.health_stomachache_emoji, R.string.health_stomachache)
        HealthState.TOOTHACHE   -> HealthState(R.string.health_toothache_emoji, R.string.health_toothache)
        HealthState.FATIGUE -> HealthState(R.string.health_fatigue_emoji, R.string.health_fatigue)
        HealthState.ILLNESS -> HealthState(R.string.health_illness_emoji, R.string.health_illness)
        HealthState.OTHER -> HealthState(R.string.health_other_emoji, R.string.health_other)
    }
