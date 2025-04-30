package soft.divan.moodtracker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import soft.divan.moodtracker.core.navigation.FeatureEntry


@Composable
fun MtNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController,
    feedEntry: FeatureEntry,
    analyticsEntry: FeatureEntry,
    calendarEntry: FeatureEntry,
    settingsEntry: FeatureEntry,
) {
    NavHost(
        navController = navController,
        startDestination = feedEntry.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        with(feedEntry) { composable(navController) }
        with(analyticsEntry) { composable(navController) }
        with(calendarEntry) { composable(navController) }
        with(settingsEntry) { composable(navController) }
    }
}