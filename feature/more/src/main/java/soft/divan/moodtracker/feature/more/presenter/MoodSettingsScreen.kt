package soft.divan.moodtracker.feature.more.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.feature.more.R
import soft.divan.moodtracker.feature.more.domain.ThemeMode

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DayMoodSelectorPreview() {
    MoodTrackerTheme {
        MoodSettingsScreen(
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }

}
@Composable
fun MoodSettingsScreen(
    navController: NavController,
    viewModel: MoodSettingsViewModel = hiltViewModel()
) {
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(stringResource(R.string.choice_theme), style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        ThemeSwitchItem(
            title = stringResource(R.string.light),
            selected = themeMode == ThemeMode.LIGHT,
            onClick = { viewModel.onThemeSelected(ThemeMode.LIGHT) }
        )
        ThemeSwitchItem(
            title = stringResource(R.string.dark),
            selected = themeMode == ThemeMode.DARK,
            onClick = { viewModel.onThemeSelected(ThemeMode.DARK) }
        )
        ThemeSwitchItem(
            title = stringResource(R.string.system),
            selected = themeMode == ThemeMode.SYSTEM,
            onClick = { viewModel.onThemeSelected(ThemeMode.SYSTEM) }
        )
    }
}

@Composable
fun ThemeSwitchItem(title: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, modifier = Modifier.weight(1f))
        RadioButton(selected = selected, onClick = onClick)
    }
}
