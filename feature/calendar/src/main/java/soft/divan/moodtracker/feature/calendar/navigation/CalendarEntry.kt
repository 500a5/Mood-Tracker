package soft.divan.moodtracker.feature.calendar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import soft.divan.moodtracker.core.navigation.FeatureEntry
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.navigation.compose.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import soft.divan.moodtracker.feature.mood_calendar.R
import soft.divan.moodtracker.feature.calendar.presenter.MoodCalendarScreen


object CalendarEntry : FeatureEntry {
    override val route = "Calendar"
    override val icon = Icons.Rounded.CalendarMonth
    @Composable
    override fun label() = stringResource(R.string.calendar)


    override fun NavGraphBuilder.composable(navController: NavController) {
        composable(CalendarEntry.route) {
            MoodCalendarScreen(navController)
        }
    }
}
