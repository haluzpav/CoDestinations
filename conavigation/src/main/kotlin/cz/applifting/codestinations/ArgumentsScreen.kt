package cz.applifting.codestinations

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation

fun NavGraphBuilder.argumentsNavigation(navController: NavController) {
    navigation(startDestination = "arguments_root", "arguments") {
        composable("arguments_root") {
            ArgumentsScreen(navController)
        }
        composable(
            "arguments_sub_screen/{number}?someData={someData}&text={text}",
            arguments = listOf(
                navArgument("number") {
                    type = NavType.IntType
                },
                navArgument("someData") {
                    // TODO not so simple 🙈 https://stackoverflow.com/questions/65610003/pass-parcelable-argument-with-compose-navigation
                    type = NavType.SerializableType(SomeData::class.java)
                    nullable = true
                },
                navArgument("text") {
                    type = NavType.StringType
                    nullable = true
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://codestinations.applifting.cz/arguments_sub_screen/{number}?text={text}"
                },
            ),
        ) {
            val number = it.arguments?.getInt("number") as Int
            val someData = it.arguments?.getSerializable("someData") as? SomeData
            val text = it.arguments?.getString("text")
            if (text != null) {
                ArgumentsSubScreen(
                    number = number,
                    someData = someData,
                    text = text,
                )
            } else {
                ArgumentsSubScreen(
                    number = number,
                    someData = someData,
                )
            }
        }
    }
}

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
                navController.navigate(
                    "arguments_sub_screen/${argumentNumber.value}",
                    // "arguments_sub_screen/${argumentNumber.value}?text=My own Hello 😘",
                )
            }
        ) {
            Text("Go to sub-screen")
        }
    }
}

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
