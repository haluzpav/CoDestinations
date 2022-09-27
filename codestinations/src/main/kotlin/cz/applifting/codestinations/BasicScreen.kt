package cz.applifting.codestinations

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import cz.applifting.codestinations.destinations.BasicSubScreenDestination

@RootNavGraph // sets BasicNavGraph as a nested nav graph of RootNavGraph
@NavGraph // marks BasicNavGraph as a NavGraph annotation
annotation class BasicNavGraph(
    val start: Boolean = false,
)

@BasicNavGraph(start = true)
@Destination
@Composable
fun BasicScreen(navController: NavController) {
    Column {
        Text("Basic screen")
        Button(onClick = { navController.navigate(BasicSubScreenDestination) }) {
            Text("Go to sub-screen")
        }
    }
}

@BasicNavGraph
@Destination
@Composable
fun BasicSubScreen() {
    Text("Basic sub-screen")
}
