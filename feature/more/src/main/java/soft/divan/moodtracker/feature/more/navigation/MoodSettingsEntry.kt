package soft.divan.moodtracker.feature.more.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import soft.divan.moodtracker.core.navigation.FeatureEntry
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.composable
import soft.divan.moodtracker.feature.more.R

import soft.divan.moodtracker.feature.more.presenter.MoodSettingsScreen


object MoreEntry : FeatureEntry {
    override val route = "more"
    override val icon = Icons.Default.MoreHoriz

    @Composable
    override fun label() = stringResource(R.string.more)

    override fun NavGraphBuilder.composable(navController: NavController) {
        composable(MoreEntry.route) {
            MoodSettingsScreen(navController)
        }
    }
}
