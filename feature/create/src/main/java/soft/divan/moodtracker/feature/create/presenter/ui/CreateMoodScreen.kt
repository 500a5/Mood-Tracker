package soft.divan.moodtracker.feature.create.presenter.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import soft.divan.moodtracker.core.model.DayMoodRating
import soft.divan.moodtracker.core.model.EmotionCategory
import soft.divan.moodtracker.core.model.HealthState
import soft.divan.moodtracker.core.model.HobbyCategory
import soft.divan.moodtracker.core.model.NutritionQuality
import soft.divan.moodtracker.core.model.SleepQuality
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
    var currentMood by remember { mutableStateOf(DayMoodRating.NORMAL) }
    var sleepQuality by remember {
        mutableStateOf(
            SleepQuality(
                durationInHours = 7,
                wentToBedLate = false,
                wokeUpOften = false,
                wokeUpTired = false
            )
        )
    }
    val selectedEmotionCategory = remember { mutableStateListOf<EmotionCategory>() }

    val selectedNutrition = remember { mutableStateListOf<NutritionQuality>() }

    SleepQualitySection(sleepQuality = sleepQuality, onChange = { sleepQuality = it })
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
                selectedMood = currentMood,
                onMoodSelected = { newMood -> currentMood = newMood }
            )



            SleepQualitySection(
                sleepQuality = sleepQuality,
                onChange = { newSleepQuality -> sleepQuality = newSleepQuality })

            EmotionSelection(selected = selectedEmotionCategory,
                onSelected = { emotion ->
                    if (selectedEmotionCategory.contains(emotion)) {
                        selectedEmotionCategory.remove(emotion)
                    } else {
                        selectedEmotionCategory.add(emotion)
                    }
                })


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

            val selectedHobbies = remember { mutableStateListOf<HobbyCategory>() }
            HobbyCategorySelection(selected = selectedHobbies, onSelected = { hobby ->
                if (selectedHobbies.contains(hobby)) {
                    selectedHobbies.remove(hobby)
                } else {
                    selectedHobbies.add(hobby)
                }
            })

            val selectedHealth = remember {  mutableStateListOf<HealthState>() }
            HealthStateSelection(selected = selectedHealth, onSelected = { health ->
                if (selectedHealth.contains(health)) {
                    selectedHealth.remove(health)
                } else {
                    selectedHealth.add(health)
                }
            })
        }
    }
}


