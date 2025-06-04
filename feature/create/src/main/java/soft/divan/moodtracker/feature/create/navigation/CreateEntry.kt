package soft.divan.moodtracker.feature.create.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import soft.divan.moodtracker.core.navigation.FeatureEntry
import soft.divan.moodtracker.feature.create.R
import soft.divan.moodtracker.feature.create.presenter.ui.CreateMoodScreen


object CreateEntry : FeatureEntry {
    override val route = "create"
    override val icon = Icons.Default.EditNote

    @Composable
    override fun label() = stringResource(R.string.create)

    override fun NavGraphBuilder.composable(navController: NavController) {
        composable(CreateEntry.route) {
            CreateMoodScreen(navController = navController)
        }
    }
}
