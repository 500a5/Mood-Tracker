package soft.divan.moodtracker.feature.create.presenter.data

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.model.SleepQuality
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Сон",
            style = MaterialTheme.typography.titleMedium
        )

        // Длительность сна
        Column {
            Text(stringResource(R.string.number_of_hours_of_sleep, sleepQuality.durationInHours))
            Slider(
                value = sleepQuality.durationInHours.toFloat(),
                onValueChange = { newHours ->
                    onChange(sleepQuality.copy(durationInHours = newHours.toInt()))
                },
                valueRange = 0f..20f,
                steps = 19
            )
        }

        // Флажки
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = sleepQuality.wentToBedLate,
                onCheckedChange = { onChange(sleepQuality.copy(wentToBedLate = it)) }
            )
            Text(stringResource(R.string.went_to_bed_late))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = sleepQuality.wokeUpOften,
                onCheckedChange = { onChange(sleepQuality.copy(wokeUpOften = it)) }
            )
            Text(stringResource(R.string.woke_up_at_night))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = sleepQuality.wokeUpTired,
                onCheckedChange = { onChange(sleepQuality.copy(wokeUpTired = it)) }
            )
            Text(stringResource(R.string.woke_up_tired))
        }
    }
}
