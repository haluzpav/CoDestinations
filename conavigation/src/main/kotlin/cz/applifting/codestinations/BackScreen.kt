package cz.applifting.codestinations

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

fun NavGraphBuilder.backNavigation(navController: NavController) {
    navigation(startDestination = "back_root", "back") {
        val resultChannel = Channel<Int?>(Channel.CONFLATED)
        composable("back_root") {
            BackScreen(navController, resultChannel.receiveAsFlow())
        }
        composable("back_sub_screen") {
            BackSubScreen(navController, resultChannel)
        }
    }
}

@Composable
fun BackScreen(
    navController: NavController,
    results: Flow<Int?>,
) {
    val result = remember { mutableStateOf("") }
    LaunchedEffect(results) {
        results.collect { result.value = it?.let { "value: $it" } ?: "canceled" }
    }
    Column {
        Text("Back screen")
        Text("Result from sub-screen: ${result.value}")
        Button(onClick = { navController.navigate("back_sub_screen") }) {
            Text("Go to sub-screen")
        }
    }
}

@Composable
fun BackSubScreen(
    navController: NavController,
    resultSender: SendChannel<Int?>,
) {
    val result = remember { mutableStateOf(0) }
    BackHandler {
        resultSender.trySend(null)
        navController.navigateUp()
    }
    Column {
        Text("Back sub-screen")
        Button(onClick = { result.value += 1 }) {
            Text("Result to return: ${result.value}")
        }
        Button(
            onClick = {
                resultSender.trySend(result.value)
                navController.navigateUp()
            }
        ) {
            Text("Navigate back with result")
        }
    }
}
