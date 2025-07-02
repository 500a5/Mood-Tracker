package soft.divan.moodtracker.feature.calendar.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import soft.divan.data_ui.data.mapToPresenter
import soft.divan.designsystem.component.LoadingProgressBar
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.domain.model.DailyMoodEntry
import soft.divan.moodtracker.core.domain.model.DayMoodRating
import soft.divan.moodtracker.feature.calendar.model.DailyMoodEntriesUiState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CalendarScreenPreview() {
    val sampleEntries = listOf(
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(3), moodRating = DayMoodRating.GOOD),
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(12), moodRating = DayMoodRating.AWFUL),
        DailyMoodEntry(date = LocalDate.now().withDayOfMonth(25), moodRating = DayMoodRating.AMAZING)
    )
    MoodTrackerTheme {
        CalendarContent(moodEntries = sampleEntries)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel(),

    ) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState.value) {
        is DailyMoodEntriesUiState.Error -> {}
        is DailyMoodEntriesUiState.Loading -> {
            LoadingProgressBar()
        }

        is DailyMoodEntriesUiState.Success -> {
            val list = (uiState.value as DailyMoodEntriesUiState.Success).dailyMoodEntry
            CalendarContent(list, modifier = modifier)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarContent(
    moodEntries: List<DailyMoodEntry>,
    modifier: Modifier = Modifier
) {
    var currentMonth by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }

    val daysInMonth = remember(currentMonth) {
        val days = mutableListOf<LocalDate>()
        val firstDay = currentMonth
        val totalDays = firstDay.lengthOfMonth()
        repeat(totalDays) { day ->
            days.add(firstDay.plusDays(day.toLong()))
        }
        days
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CalendarHeader(
            currentMonth = currentMonth,
            onPrevious = { currentMonth = currentMonth.minusMonths(1) },
            onNext = { currentMonth = currentMonth.plusMonths(1) }
        )

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                items(daysInMonth) { date ->
                    val entry = moodEntries
                        .filter { it.date == date }
                        .maxByOrNull { it.date }

                    DayCell(date = date, entry = entry)
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarHeader(
    currentMonth: LocalDate,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onPrevious) {
            Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Previous")
        }
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.getDefault())),
            style = MaterialTheme.typography.titleMedium,
        )
        IconButton(onClick = onNext) {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Next")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayCell(date: LocalDate, entry: DailyMoodEntry?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    if (entry == null) Color.LightGray
                    else MaterialTheme.colorScheme.secondaryContainer
                )
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            if (entry == null) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                val mood = entry.moodRating.mapToPresenter()
                Text(
                    text = stringResource(id = mood.emojiResId),
                    fontSize = 20.sp
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
