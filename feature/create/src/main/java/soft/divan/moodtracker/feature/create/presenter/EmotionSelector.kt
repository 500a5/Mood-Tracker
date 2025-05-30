package soft.divan.moodtracker.feature.create.presenter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.model.EmotionCategory
import soft.divan.moodtracker.feature.create.R

import soft.divan.moodtracker.feature.create.presenter.data.EmotionMapper
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EmotionSelectionPreview() {
    val selected = remember { mutableStateListOf<EmotionCategory>() }
    MoodTrackerTheme {
        EmotionSelection(
            selectedEmotions = selected,
            onEmotionToggle = { emotion ->
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
    selectedEmotions: List<EmotionCategory>,
    onEmotionToggle: (EmotionCategory) -> Unit,
    modifier: Modifier = Modifier
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
            text = stringResource(R.string.emotions),
            style = MaterialTheme.typography.titleMedium
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            EmotionCategory.entries
                .forEach { emotion ->
                    val isSelected = selectedEmotions.contains(emotion)
                    FilterChip(
                        selected = isSelected,
                        onClick = { onEmotionToggle(emotion) },
                        label = { Text(text = "${emotion.mapToPresenter().emoji} ${stringResource(id = emotion.mapToPresenter().labelResId)}") },
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline // Можно менять цвет в зависимости от состояния, либо оставить один цвет
                        )
                    )
                }
        }
    }

}
