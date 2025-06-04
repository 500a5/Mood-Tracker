package soft.divan.moodtracker.feature.create.presenter.data

import androidx.annotation.StringRes
import soft.divan.moodtracker.core.model.NutritionQuality
import soft.divan.moodtracker.feature.create.R

data class Nutrition(
    @StringRes val emojiResId: Int,
    @StringRes val labelResId: Int,
)

fun NutritionQuality.mapToPresenter(): soft.divan.moodtracker.feature.create.presenter.data.Nutrition =
    when (this) {
        NutritionQuality.HEALTHY -> Nutrition(
            R.string.nutrition_healthy_emoji,
            R.string.nutrition_healthy
        )

        NutritionQuality.OVEREAT -> Nutrition(
            R.string.nutrition_overeat_emoji,
            R.string.nutrition_overeat
        )

        NutritionQuality.SKIPPED_MEAL -> Nutrition(
            R.string.nutrition_skipped_meal_emoji,
            R.string.nutrition_skipped_meal
        )

        NutritionQuality.JUNK_FOOD -> Nutrition(
            R.string.nutrition_junk_food_emoji,
            R.string.nutrition_junk_food
        )
    }