package soft.divan.moodtracker.feature.create.presenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import soft.divan.designsystem.component.FilterChipFlow
import soft.divan.designsystem.component.LabeledBorderedContainer
import soft.divan.moodtracker.core.model.HealthState
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewHealthStateSelection() {
    val selected = remember { mutableStateListOf<HealthState>() }

    HealthStateSelection(
        selected = selected,
        onSelected = { state ->
            if (selected.contains(state)) {
                selected.remove(state)
            } else {
                selected.add(state)
            }
        }
    )
}

@Composable
fun HealthStateSelection(
    selected: List<HealthState>,
    onSelected: (HealthState) -> Unit,
    modifier: Modifier = Modifier
) {
    LabeledBorderedContainer(
        titleResId = R.string.health,
        modifier = modifier
    ) {
        FilterChipFlow(
            items = HealthState.entries,
            selected = selected,
            onClick = onSelected,
            labelProvider = { state ->
                val presenter = state.mapToPresenter()
                "${stringResource(presenter.emojiResId)} ${stringResource(presenter.labelResId)}"
            }
        )
    }
}
