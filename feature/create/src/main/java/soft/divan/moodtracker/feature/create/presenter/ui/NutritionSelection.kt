package soft.divan.moodtracker.feature.create.presenter.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import soft.divan.designsystem.component.FilterChipFlow
import soft.divan.designsystem.component.LabeledBorderedContainer
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.model.NutritionQuality
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NutritionSelectorPreview() {
    val selectedNutrition = remember { mutableStateListOf<NutritionQuality>() }
    MoodTrackerTheme {
        NutritionSelection(
            selected = selectedNutrition,
            onSelected = { nutritionQuality ->
                if (selectedNutrition.contains(nutritionQuality)) {
                    selectedNutrition.remove(nutritionQuality)
                } else {
                    selectedNutrition.add(nutritionQuality)
                }
            }
        )
    }
}

@Composable
fun NutritionSelection(
    selected: List<NutritionQuality>,
    onSelected: (NutritionQuality) -> Unit,
    modifier: Modifier = Modifier
) {
    LabeledBorderedContainer(titleResId = R.string.nutrition, modifier = modifier) {
        FilterChipFlow(
            items = NutritionQuality.entries,
            selected = selected,
            onClick = onSelected,
            labelProvider = {
                val p = it.mapToPresenter()
                "${stringResource(p.emojiResId)} ${stringResource(p.labelResId)}"
            }
        )
    }
}