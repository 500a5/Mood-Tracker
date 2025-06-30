package soft.divan.moodtracker.feature.create.presenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import soft.divan.data_ui.data.mapToPresenter
import soft.divan.designsystem.component.FilterChipFlow
import soft.divan.designsystem.component.LabeledBorderedContainer
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.domain.model.HabitType
import soft.divan.moodtracker.feature.create.R

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewHabitSelection() {
    val selected = remember { mutableStateListOf<HabitType>() }
    MoodTrackerTheme {
        HabitSelection(
            selected = selected,
            onSelected = { habit ->

                if (selected.contains(habit)) {
                    selected.remove(habit)
                } else {
                    selected.add(habit)
                }
            }
        )
    }
}

@Composable
fun HabitSelection(
    selected: List<HabitType>,
    onSelected: (HabitType) -> Unit,
    modifier: Modifier = Modifier
) {
    LabeledBorderedContainer(
        titleResId = R.string.habits,
        modifier = modifier
    ) {
        FilterChipFlow(
            items = HabitType.entries,
            selected = selected,
            onClick = { onSelected(it) },
            labelProvider = {
                val presenter = it.mapToPresenter()
                "${stringResource(presenter.emojiResId)} ${stringResource(presenter.labelResId)}"
            }
        )
    }
}
