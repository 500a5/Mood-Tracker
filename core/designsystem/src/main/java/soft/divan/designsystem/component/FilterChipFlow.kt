package soft.divan.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soft.divan.designsystem.theme.MoodTrackerTheme


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun FilterChipFlowPreview() {
    val sampleItems = listOf("Work", "Sport", "Rest", "Study", "Friends", "Sleep")
    var selectedItems by remember { mutableStateOf(listOf<String>()) }


    MoodTrackerTheme {
        FilterChipFlow(
            items = sampleItems,
            selected = selectedItems,
            onClick = { item ->
                selectedItems = if (selectedItems.contains(item)) {
                    selectedItems - item
                } else {
                    selectedItems + item
                }
            },
            labelProvider = { it }
        )
    }
}


@Composable
fun <T> FilterChipFlow(
    items: List<T>,
    selected: List<T>,
    onClick: (T) -> Unit,
    labelProvider: @Composable (T) -> String,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            val isSelected = selected.contains(item)
            FilterChip(
                modifier = Modifier.height(32.dp),
                selected = isSelected,
                onClick = { onClick(item) },
                label = { Text(text = labelProvider(item)) },
                border = BorderStroke(
                    width = 1.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                )
            )

        }
    }
}
