package soft.divan.moodtracker.feature.create.presenter

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import soft.divan.designsystem.component.BackButton
import soft.divan.designsystem.component.CenterAlignedTopAppBarBase
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.core.model.DayMoodRating
import soft.divan.moodtracker.feature.create.presenter.data.mapToPresentr


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
fun CreateMoodScreen(modifier: Modifier = Modifier, navController: NavController, viewModel: CreateMoodViewModel = hiltViewModel()) {
    var currentMood by remember { mutableStateOf(DayMoodRating.NORMAL) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarBase(title = "Как прошел твой день?", navigationIcon = {
                BackButton(onClick = { navController.popBackStack() })
            })
        },
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            DayMoodSelector(
                selectedMood = currentMood,
                onMoodSelected = { newMood -> currentMood = newMood }
            )
        }
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
                    .clickable { onMoodSelected(mood) }
                    .padding(horizontal = 0.dp)
                    .border(
                        width = if (mood == selectedMood) 2.dp else 0.dp,
                        color = if (mood == selectedMood) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = mood.mapToPresentr().emoji,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource( mood.mapToPresentr().labelResId),
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = if (mood == selectedMood) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}