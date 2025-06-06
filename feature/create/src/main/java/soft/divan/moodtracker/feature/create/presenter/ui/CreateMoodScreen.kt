package soft.divan.moodtracker.feature.create.presenter.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import soft.divan.designsystem.component.BackButton
import soft.divan.designsystem.component.CenterAlignedTopAppBarBase
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.CreateMoodViewModel


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun EntriesScreenPreview() {
    MoodTrackerTheme {
        CreateMoodScreen(
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateMoodScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CreateMoodViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier.imePadding(),
        topBar = {
            CenterAlignedTopAppBarBase(
                title = stringResource(R.string.how_was_your_day),
                navigationIcon = {
                    BackButton(onClick = { navController.popBackStack() })
                })
        },
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DayMoodSelector(
                selectedMood = uiState.moodRating,
                onMoodSelected = { dayMoodRating -> viewModel.updateMood(dayMoodRating) }
            )

            SleepQualitySection(
                sleepQuality = uiState.sleep,
                onChange = { sleep -> viewModel.updateSleepQuality(sleep) }
            )

            EmotionSelection(
                selected = uiState.emotions,
                onSelected = { emotions -> viewModel.toggleEmotion(emotions) }
            )

            NutritionSelection(
                selected = uiState.nutrition,
                onSelected = { nutrition -> viewModel.toggleNutrition(nutrition) }
            )

            HobbyCategorySelection(
                selected = uiState.hobbies,
                onSelected = { hobbis -> viewModel.toggleHobby(hobbis) }
            )

            HealthStateSelection(
                selected = uiState.health,
                onSelected = { health -> viewModel.toggleHealthState(health) }
            )

            WeatherSelection(
                selected = uiState.weather,
                onSelected = { weather -> viewModel.updateWeather(weather) }
            )

            HabitSelection(
                selected = uiState.habits,
                onSelected = { habits -> viewModel.toggleHabit(habits) }
            )

            NoteInput(
                note = uiState.note,
                onNoteChange = { note -> viewModel.updateNote(note) }
            )

            SaveButton(onClick = { viewModel.saveDay() })
        }
    }
}

@Composable
fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = stringResource(R.string.save),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


