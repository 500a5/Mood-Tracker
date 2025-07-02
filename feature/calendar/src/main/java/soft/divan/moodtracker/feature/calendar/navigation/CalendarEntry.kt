package soft.divan.moodtracker.feature.calendar.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import soft.divan.moodtracker.core.navigation.FeatureEntry
import soft.divan.moodtracker.feature.calendar.presenter.CalendarScreen
import soft.divan.moodtracker.feature.mood_calendar.R


object CalendarEntry : FeatureEntry {
    override val route = "Calendar"
    override val icon = Icons.Rounded.CalendarMonth
    @Composable
    override fun label() = stringResource(R.string.calendar)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun NavGraphBuilder.composable(navController: NavController) {
        composable(CalendarEntry.route) {
            CalendarScreen(navController = navController)
        }
    }
}
