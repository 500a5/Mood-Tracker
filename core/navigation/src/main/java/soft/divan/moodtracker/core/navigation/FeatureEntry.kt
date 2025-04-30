package soft.divan.moodtracker.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder


interface FeatureEntry {
    val route: String
    val icon: ImageVector
    @Composable
    fun label(): String

    fun NavGraphBuilder.composable(navController: NavController)

}
