package soft.divan.moodtracker.feature.create.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.model.DayMoodRating
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DayMoodSelectorPreview() {
    var previewSelectedMood by remember {
        mutableStateOf(
            DayMoodRating.NORMAL
        )

    }

    MoodTrackerTheme {
        DayMoodSelector(
            selectedMood = previewSelectedMood,
            onMoodSelected = { }
        )
    }

}


@Composable
fun DayMoodSelector(
    selectedMood: DayMoodRating,
    onMoodSelected: (DayMoodRating) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DayMoodRating.entries.forEach { mood ->

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onMoodSelected(mood) }
                    .padding(horizontal = 0.dp)
                    .border(
                        width = if (mood == selectedMood) 2.dp else 0.dp,
                        color = if (mood == selectedMood) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        color = if (mood == selectedMood) MaterialTheme.colorScheme.surfaceContainer else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(mood.mapToPresenter().emojiResId),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(mood.mapToPresenter().labelResId),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = if (mood == selectedMood) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}