package soft.divan.moodtracker.feature.analytics.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import soft.divan.moodtracker.core.navigation.FeatureEntry
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.InsertChartOutlined
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.composable
import soft.divan.moodtracker.feature.analytics.R
import soft.divan.moodtracker.feature.mood_analytics.presenter.MoodAnalyticsScreen


object AnalyticsEntry : FeatureEntry {
    override val route = "analytics"
    override val icon = Icons.Default.InsertChartOutlined
    @Composable
    override fun label() = stringResource(R.string.Analytics)

    override fun NavGraphBuilder.composable(navController: NavController) {
        composable(AnalyticsEntry.route) {
            MoodAnalyticsScreen(navController)
        }
    }
}
