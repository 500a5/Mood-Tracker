package soft.divan.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.designsystem.R

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LabeledBorderedContainerPreview() {
    MoodTrackerTheme {
        LabeledBorderedContainer(
            titleResId = R.string.test,
            modifier = Modifier
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Example content inside the container.")
                Button(onClick = {}) {
                    Text("Action")
                }
            }
        }
    }
}


@Composable
fun LabeledBorderedContainer(
    @StringRes titleResId: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
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
            text = stringResource(id = titleResId),
            style = MaterialTheme.typography.titleMedium,
        )
        content()
    }
}
