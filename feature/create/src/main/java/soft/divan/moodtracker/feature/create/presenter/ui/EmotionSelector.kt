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
import soft.divan.moodtracker.core.model.EmotionCategory
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EmotionSelectionPreview() {
    val selected = remember { mutableStateListOf<EmotionCategory>() }
    MoodTrackerTheme {
        EmotionSelection(
            selected = selected,
            onSelected = { emotion ->
                if (selected.contains(emotion)) {
                    selected.remove(emotion)
                } else {
                    selected.add(emotion)
                }
            }
        )
    }
}

@Composable
fun EmotionSelection(
    selected: List<EmotionCategory>,
    onSelected: (EmotionCategory) -> Unit,
    modifier: Modifier = Modifier
) {

    LabeledBorderedContainer(titleResId = R.string.emotions, modifier = modifier) {
        FilterChipFlow(
            items = EmotionCategory.entries,
            selected = selected,
            onClick = onSelected,
            labelProvider = {
                val p = it.mapToPresenter()
                "${stringResource(p.emojiResId)} ${stringResource(p.labelResId)}"
            }
        )
    }
}
