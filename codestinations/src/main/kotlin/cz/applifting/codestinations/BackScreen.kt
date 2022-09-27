package cz.applifting.codestinations

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import cz.applifting.codestinations.destinations.BackSubScreenDestination

@RootNavGraph
@NavGraph
annotation class BackNavGraph(
    val start: Boolean = false,
)

@BackNavGraph(start = true)
@Destination
@Composable
fun BackScreen(
    navController: NavController,
    resultRecipient: ResultRecipient<BackSubScreenDestination, Int>,
) {
    val result = remember { mutableStateOf("") }
    resultRecipient.onNavResult { navResult ->
        when (navResult) {
            NavResult.Canceled -> result.value = "canceled"
            is NavResult.Value -> result.value = "value: ${navResult.value}"
        }
    }
    Column {
        Text("Back screen")
        Text("Result from sub-screen: ${result.value}")
        Button(onClick = { navController.navigate(BackSubScreenDestination) }) {
            Text("Go to sub-screen")
        }
    }
}

@BackNavGraph
@Destination
@Composable
fun BackSubScreen(resultNavigator: ResultBackNavigator<Int>) {
    val result = remember { mutableStateOf(0) }
    Column {
        Text("Back sub-screen")
        Button(onClick = { result.value += 1 }) {
            Text("Result to return: ${result.value}")
        }
        Button(onClick = { resultNavigator.navigateBack(result.value) }) {
            Text("Navigate back with result")
        }
    }
}
