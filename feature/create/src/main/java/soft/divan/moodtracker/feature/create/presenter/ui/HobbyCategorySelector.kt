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
import soft.divan.moodtracker.core.domain.model.HobbyCategory
import soft.divan.moodtracker.feature.create.R

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HobbySelectionPreview() {
    val selected = remember { mutableStateListOf<HobbyCategory>() }
    MoodTrackerTheme {
        HobbyCategorySelection(
            selected = selected,
            onSelected = { hobby ->
                if (selected.contains(hobby)) {
                    selected.remove(hobby)
                } else {
                    selected.add(hobby)
                }
            }
        )
    }
}

@Composable
fun HobbyCategorySelection(
    selected: List<HobbyCategory>,
    onSelected: (HobbyCategory) -> Unit,
    modifier: Modifier = Modifier
) {

    LabeledBorderedContainer(titleResId = R.string.hobby, modifier = modifier) {
        FilterChipFlow(
            items = HobbyCategory.entries,
            selected = selected,
            onClick = onSelected,
            labelProvider = {
                val p = it.mapToPresenter()
                "${stringResource(p.emojiResId)} ${stringResource(p.labelResId)}"
            }
        )
    }
}