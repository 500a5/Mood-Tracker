package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.model.HealthState
import soft.divan.moodtracker.feature.create.R

data class HealthState(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int
)

fun HealthState.mapToPresenter(): soft.divan.moodtracker.feature.create.presenter.data.HealthState =
    when (this) {
        HealthState.HEALTHY -> HealthState(R.string.health_healthy_emoji, R.string.health_healthy)
        HealthState.HEADACHE -> HealthState(R.string.health_headache_emoji, R.string.health_headache)
        HealthState.STOMACHACHE -> HealthState(R.string.health_stomachache_emoji, R.string.health_stomachache)
        HealthState.TOOTHACHE   -> HealthState(R.string.health_toothache_emoji, R.string.health_toothache)
        HealthState.FATIGUE -> HealthState(R.string.health_fatigue_emoji, R.string.health_fatigue)
        HealthState.ILLNESS -> HealthState(R.string.health_illness_emoji, R.string.health_illness)
        HealthState.OTHER -> HealthState(R.string.health_other_emoji, R.string.health_other)
    }
