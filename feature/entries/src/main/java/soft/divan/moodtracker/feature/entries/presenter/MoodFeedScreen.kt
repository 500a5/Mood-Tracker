package soft.divan.moodtracker.feature.entries.presenter

import android.R.attr.entries
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import soft.divan.data_ui.data.DayMoodRating

import soft.divan.data_ui.data.mapToPresenter
import soft.divan.designsystem.component.LoadingProgressBar
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.model.EmotionCategory
import soft.divan.moodtracker.core.domain.model.SleepQuality
import soft.divan.moodtracker.feature.entries.R
import soft.divan.moodtracker.feature.entries.model.DailyMoodEntriesUiState
import java.time.LocalDate


@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EntriesScreenPreview() {
    val sampleEntry = DailyMoodEntry(
        id = "1",
        emotions = listOf(EmotionCategory.HAPPY, EmotionCategory.PRODUCTIVITY, EmotionCategory.ANGRY),
        sleep = SleepQuality ()

    )

    val sampleUiState = DailyMoodEntriesUiState.Success(dailyMoodEntry = listOf(sampleEntry, sampleEntry.copy(id = "2"), sampleEntry.copy(id = "3")))



    MoodTrackerTheme {
        EntriesContent(
            navController = rememberNavController(),
            uiState = sampleUiState
        )
    }
}



@Composable
fun EntriesScreen(navController: NavController, viewModel: MoodEntriesViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EntriesContent(navController, uiState)

}

@Composable
fun EntriesContent(navController: NavController, uiState: DailyMoodEntriesUiState) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CreateNewEntryButton(onClick = {
            navController.navigate("create")
        })

        when (uiState) {
            is DailyMoodEntriesUiState.Error -> {

            }

            is DailyMoodEntriesUiState.Loading -> {
                LoadingProgressBar()
            }

            is DailyMoodEntriesUiState.Success -> {
                val entries = (uiState as DailyMoodEntriesUiState.Success).dailyMoodEntry
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    items(entries, key = { it.id }) { entry ->
                        MoodEntryItem(entry = entry, onClick = {
                            // возможно: navController.navigate("detail/${entry.id}")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CreateNewEntryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.create_entry))
        }
    }
}

@Composable
fun MoodEntryItem(
    entry: DailyMoodEntry,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth().padding(4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = entry.date.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = entry.moodRating.mapToPresenter().emojiResId),
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = entry.moodRating.mapToPresenter().labelResId),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(Modifier.height(8.dp))

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                entry.emotions.take(3).forEach {
                    val presenter = it.mapToPresenter()
                    AssistChip(
                        onClick = {},
                        label = {
                            Text("${stringResource(presenter.emojiResId)} ${stringResource(presenter.labelResId)}")
                        }
                    )
                }
            }
        }
    }
}