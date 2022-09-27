package cz.applifting.codestinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CrossroadScreen(
    navController: NavController,
) {
    Crossroad(
        title = "Compose Navigation",
        navigateToBasic = { navController.navigate("basic") },
        navigateToArgs = { navController.navigate("arguments") },
        navigateToBackNav = { navController.navigate("back") },
    )
}
