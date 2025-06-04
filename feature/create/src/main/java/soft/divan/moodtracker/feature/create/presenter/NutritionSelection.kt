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
import soft.divan.moodtracker.core.model.NutritionQuality
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresenter

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NutritionSelectorPreview() {
    val selectedNutrition = remember { mutableStateListOf<NutritionQuality>() }
    MoodTrackerTheme {
        NutritionSelection(
            selected = selectedNutrition,
            onSelected = { nutritionQuality ->
                if (selectedNutrition.contains(nutritionQuality)) {
                    selectedNutrition.remove(nutritionQuality)
                } else {
                    selectedNutrition.add(nutritionQuality)
                }
            }
        )
    }
}

@Composable
fun NutritionSelection(
    selected: List<NutritionQuality>,
    onSelected: (NutritionQuality) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(12.dp)
        )
        .padding(16.dp)) {
        Text(
            text = stringResource(R.string.nutrition),
            style = MaterialTheme.typography.titleMedium
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            NutritionQuality.entries.forEach { option ->
                val isSelected = selected.contains(option)
                FilterChip(
                    selected = isSelected,
                    onClick = { onSelected(option) },
                    label = {
                        Text(text = "${option.mapToPresenter().emoji} ${stringResource(id = option.mapToPresenter().labelResId)}")
                    },
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline // Можно менять цвет в зависимости от состояния, либо оставить один цвет
                    )
                )
            }
        }
    }
}