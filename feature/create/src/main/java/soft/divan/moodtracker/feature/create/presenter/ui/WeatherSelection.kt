package soft.divan.moodtracker.feature.create.presenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import soft.divan.designsystem.component.FilterChipFlow
import soft.divan.designsystem.component.LabeledBorderedContainer
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.model.WeatherType
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewWeatherSelection() {
    var selected by remember { mutableStateOf<WeatherType?>(WeatherType.SUNNY) }
    MoodTrackerTheme {
        WeatherSelection(
            selected = selected,
            onSelected = { selected = it }
        )
    }
}

@Composable
fun WeatherSelection(
    selected: WeatherType?,
    onSelected: (WeatherType) -> Unit,
    modifier: Modifier = Modifier
) {
    LabeledBorderedContainer(
        titleResId = R.string.weather,
        modifier = modifier
    ) {
        FilterChipFlow(
            items = WeatherType.entries.toList(),
            selected = listOfNotNull(selected),
            onClick = { onSelected(it) },
            labelProvider = {
                val presenter = it.mapToPresenter()
                "${stringResource(presenter.emojiResId)} ${stringResource(presenter.labelResId)}"
            }
        )
    }
}
