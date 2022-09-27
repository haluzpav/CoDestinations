package cz.applifting.codestinations

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.basicNavigation(navController: NavController) {
    navigation(startDestination = "basic_root", "basic") {
        composable("basic_root") {
            BasicScreen(navController)
        }
        composable("basic_sub_screen") {
            BasicSubScreen()
        }
    }
}

@Composable
fun BasicScreen(navController: NavController) {
    Column {
        Text("Basic screen")
        Button(onClick = { navController.navigate("basic_sub_screen") }) {
            Text("Go to sub-screen")
        }
    }
}

@Composable
fun BasicSubScreen() {
    Text("Basic sub-screen")
}
