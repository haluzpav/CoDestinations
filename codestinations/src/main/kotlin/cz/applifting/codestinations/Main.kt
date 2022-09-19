package cz.applifting.codestinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun Main() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}
