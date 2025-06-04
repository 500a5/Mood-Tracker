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
import soft.divan.moodtracker.core.model.HobbyCategory
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun HobbySelectionPreview() {
    val selected = remember { mutableStateListOf<HobbyCategory>() }
    MoodTrackerTheme {
        HobbyCategorySelection(
            selectedHobbies = selected,
            onEmotionToggle = { hobby ->
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
    selectedHobbies: List<HobbyCategory>,
    onEmotionToggle: (HobbyCategory) -> Unit,
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
            text = stringResource(R.string.hobby),
            style = MaterialTheme.typography.titleMedium
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            HobbyCategory.entries
                .forEach { hobby ->
                    val isSelected = selectedHobbies.contains(hobby)
                    FilterChip(
                        selected = isSelected,
                        onClick = { onEmotionToggle(hobby) },
                        label = { Text(text = "${hobby.mapToPresenter().emoji} ${stringResource(id = hobby.mapToPresenter().labelResId)}") },
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline // Можно менять цвет в зависимости от состояния, либо оставить один цвет
                        )
                    )
                }
        }
    }

}