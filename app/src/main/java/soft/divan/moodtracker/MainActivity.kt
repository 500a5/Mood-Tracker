package soft.divan.moodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.feature.analytics.navigation.AnalyticsEntry
import soft.divan.moodtracker.feature.calendar.navigation.CalendarEntry
import soft.divan.moodtracker.feature.create.navigation.CreateEntry
import soft.divan.moodtracker.feature.entries.navigation.EntriesEntry
import soft.divan.moodtracker.feature.more.domain.ThemeMode
import soft.divan.moodtracker.feature.more.domain.usecase.GetThemeModeUseCase
import soft.divan.moodtracker.feature.more.navigation.MoreEntry
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var getThemeModeUseCase: GetThemeModeUseCase
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode by getThemeModeUseCase().collectAsState(initial = ThemeMode.SYSTEM)
            val isDark = when (themeMode) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }
            MoodTrackerTheme(darkTheme = isDark) {
                MainScreen(
                    entriesEntry = EntriesEntry,
                    analyticsEntry = AnalyticsEntry,
                    calendarEntry = CalendarEntry,
                    moreEntry = MoreEntry,
                    createEntry = CreateEntry,
                    modifier = Modifier
                )
            }
        }
    }
}
