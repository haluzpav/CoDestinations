package cz.applifting.codestinations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "crossroad"
    ) {
        composable("crossroad") {
            CrossroadScreen(navController)
        }
        basicNavigation(navController)
        argumentsNavigation(navController)
        backNavigation(navController)
    }
}
