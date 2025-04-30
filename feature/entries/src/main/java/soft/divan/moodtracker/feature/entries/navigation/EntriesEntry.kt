package soft.divan.moodtracker.feature.entries.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import soft.divan.moodtracker.core.navigation.FeatureEntry

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.composable
import soft.divan.moodtracker.feature.entries.R
import soft.divan.moodtracker.feature.entries.presenter.MoodFeedScreen


object EntriesEntry : FeatureEntry {
    override val route = "entries"
    override val icon = Icons.Default.EditNote

    @Composable
    override fun label() = stringResource(R.string.entries)

    override fun NavGraphBuilder.composable(navController: NavController) {
        composable(EntriesEntry.route) {
            MoodFeedScreen(navController)
        }
    }
}
