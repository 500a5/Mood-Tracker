package soft.divan.moodtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import soft.divan.moodtracker.core.navigation.FeatureEntry


@Composable
fun mtNavHost(

    navController: NavHostController,
    feedEntry: FeatureEntry,
    analyticsEntry: FeatureEntry,
    calendarEntry: FeatureEntry,
    settingsEntry: FeatureEntry,
    createEntry: FeatureEntry
) {
    NavHost(
        navController = navController,
        startDestination = feedEntry.route,
    ) {
        with(feedEntry) { composable(navController) }
        with(analyticsEntry) { composable(navController) }
        with(calendarEntry) { composable(navController) }
        with(settingsEntry) { composable(navController) }
        with(createEntry) {  composable(navController)}
    }
}