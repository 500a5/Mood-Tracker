package soft.divan.moodtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import soft.divan.designsystem.theme.MoodTrackerTheme
import soft.divan.moodtracker.feature.analytics.navigation.AnalyticsEntry
import soft.divan.moodtracker.feature.calendar.navigation.CalendarEntry
import soft.divan.moodtracker.feature.create.navigation.CreateEntry
import soft.divan.moodtracker.feature.entries.navigation.EntriesEntry
import soft.divan.moodtracker.feature.more.navigation.MoreEntry


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodTrackerTheme {
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
