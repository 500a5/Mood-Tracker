package soft.divan.moodtracker

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import soft.divan.moodtracker.core.navigation.FeatureEntry
import soft.divan.moodtracker.navigation.MtNavHost

@Composable
fun MainScreen(
    entriesEntry: FeatureEntry,
    analyticsEntry: FeatureEntry,
    calendarEntry: FeatureEntry,
    moreEntry: FeatureEntry,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val screens = listOf(entriesEntry, analyticsEntry, calendarEntry, moreEntry)
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    MtNavigationBarItem(currentRoute, screen, navController)
                }
            }
        }
    ) { innerPadding ->
        MtNavHost(
            innerPadding = innerPadding,
            navController = navController,
            feedEntry = entriesEntry,
            analyticsEntry = analyticsEntry,
            calendarEntry = calendarEntry,
            settingsEntry = moreEntry
        )
    }
}

@Composable
private fun RowScope.MtNavigationBarItem(
    currentRoute: String?,
    screen: FeatureEntry,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentRoute == screen.route,
        onClick = {
            if (currentRoute != screen.route) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        icon = { Icon(screen.icon, contentDescription = null) },
        label = { Text(screen.label()) }
    )
}
