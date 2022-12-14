package cz.applifting.codestinations

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.FULL_ROUTE_PLACEHOLDER
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import cz.applifting.codestinations.destinations.ArgumentsSubScreenDestination

@RootNavGraph
@NavGraph
annotation class ArgumentsNavGraph(
    val start: Boolean = false,
)

@ArgumentsNavGraph(start = true)
@Destination
@Composable
fun ArgumentsScreen(navController: NavController) {
    val argumentNumber = remember { mutableStateOf(0) }
    Column {
        Text("Arguments screen")
        Button(onClick = { argumentNumber.value += 1 }) {
            Text("Argument to pass: ${argumentNumber.value}")
        }
        Button(
            onClick = {
                val destination = ArgumentsSubScreenDestination(
                    number = argumentNumber.value,
                    someData = null,
                    // someData = SomeData("Pavel Havel", 123L),
                    // text = "My own Hello 😘",
                )
                navController.navigate(destination)
            }
        ) {
            Text("Go to sub-screen")
        }
    }
}

@ArgumentsNavGraph
@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://codestinations.applifting.cz/$FULL_ROUTE_PLACEHOLDER"
        )
    ]
)
@Composable
fun ArgumentsSubScreen(
    number: Int,
    someData: SomeData?,
    text: String = "Hello 🙃",
) {
    Column {
        Text("Arguments sub-screen")
        Text("text: $text")
        Text("number: $number")
        Text("someData: $someData")
    }
}
