package soft.divan.moodtracker

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    createEntry: FeatureEntry,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    val screens = listOf(entriesEntry, analyticsEntry, calendarEntry, moreEntry)
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val context = LocalContext.current

    // список маршрутов, которые отображаются в нижнем меню
    val bottomRoutes = screens.map { it.route }

// если текущий маршрут НЕ входит в нижнюю навигацию, сохраняем последний выбранный из нижнего меню
    val effectiveRoute = if (currentRoute in bottomRoutes) {
        currentRoute
    } else {
        bottomRoutes.firstOrNull { it in navController.previousBackStackEntry?.destination?.route.orEmpty() }
            ?: bottomRoutes.first()
    }

    BackHandler {
        (context as ComponentActivity).finishAffinity()
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = {contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .consumeWindowInsets(contentPadding)

            ) {
                MtNavHost(
                    navController = navController,
                    feedEntry = entriesEntry,
                    analyticsEntry = analyticsEntry,
                    calendarEntry = calendarEntry,
                    settingsEntry = moreEntry,
                    createEntry = createEntry,
                )
            }
        },
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    MtNavigationBarItem(effectiveRoute, screen, navController)
                }
            }
        }
    )
}

@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
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
                    popUpTo(0) {
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
