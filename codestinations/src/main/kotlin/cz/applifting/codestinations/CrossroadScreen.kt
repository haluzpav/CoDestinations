package cz.applifting.codestinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import cz.applifting.codestinations.destinations.ArgumentsScreenDestination
import cz.applifting.codestinations.destinations.BackScreenDestination
import cz.applifting.codestinations.destinations.BasicScreenDestination

@RootNavGraph(start = true)
@Destination
@Composable
fun CrossroadScreen(
    navController: NavController,
) {
    Crossroad(
        title = "Compose Destinations",
        navigateToBasic = { navController.navigate(BasicScreenDestination) },
        navigateToArgs = { navController.navigate(ArgumentsScreenDestination) },
        navigateToBackNav = { navController.navigate(BackScreenDestination) },
    )
}
