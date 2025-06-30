package soft.divan.moodtracker.feature.create.presenter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soft.divan.designsystem.component.LabeledBorderedContainer
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.domain.model.SleepQuality
import soft.divan.moodtracker.feature.create.R


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SleepQualitySectionPreview() {
    var previewSleepQuality by remember {
        mutableStateOf(
            SleepQuality(
                durationInHours = 10,
                wentToBedLate = true,
                wokeUpOften = false,
                wokeUpTired = true
            )
        )
    }

    MoodTrackerTheme {
        SleepQualitySection(
            sleepQuality = previewSleepQuality,
            onChange = { previewSleepQuality = it }
        )
    }

}


@Composable
fun SleepQualitySection(
    modifier: Modifier = Modifier,
    sleepQuality: SleepQuality,
    onChange: (SleepQuality) -> Unit
) {
    LabeledBorderedContainer(titleResId = R.string.dream, modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            SleepDurationSlider(
                duration = sleepQuality.durationInHours,
                onDurationChange = { onChange(sleepQuality.copy(durationInHours = it)) }
            )

            CheckboxRow(
                checked = sleepQuality.wentToBedLate,
                label = stringResource(R.string.went_to_bed_late),
                onCheckedChange = { onChange(sleepQuality.copy(wentToBedLate = it)) }
            )

            CheckboxRow(
                checked = sleepQuality.wokeUpOften,
                label = stringResource(R.string.woke_up_at_night),
                onCheckedChange = { onChange(sleepQuality.copy(wokeUpOften = it)) }
            )

            CheckboxRow(
                checked = sleepQuality.wokeUpTired,
                label = stringResource(R.string.woke_up_tired),
                onCheckedChange = { onChange(sleepQuality.copy(wokeUpTired = it)) }
            )
        }
    }
}

@Composable
private fun SleepDurationSlider(
    duration: Int,
    onDurationChange: (Int) -> Unit
) {
    Column {
        Text(stringResource(R.string.number_of_hours_of_sleep, duration))
        Slider(
            value = duration.toFloat(),
            onValueChange = { onDurationChange(it.toInt()) },
            valueRange = 0f..20f,
            steps = 19
        )
    }
}

@Composable
private fun CheckboxRow(
    checked: Boolean,
    label: String,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Checkbox(
            modifier = Modifier.height(32.dp),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(text = label)
    }
}
