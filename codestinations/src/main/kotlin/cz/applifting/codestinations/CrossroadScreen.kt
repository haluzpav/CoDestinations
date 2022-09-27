package cz.applifting.codestinations

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
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
    // stuff for deeplink
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}
    val context = LocalContext.current
    val deepLinkIntent = remember {
        Intent(
            Intent.ACTION_VIEW,
            "https://codestinations.applifting.cz/arguments_sub_screen/42?text=This is deeplink, baby!".toUri(),
            context,
            MainActivity::class.java
        )
    }

    Crossroad(
        title = "Compose Destinations",
        navigateToBasic = { navController.navigate(BasicScreenDestination) },
        navigateToArgs = { navController.navigate(ArgumentsScreenDestination) },
        navigateToBackNav = { navController.navigate(BackScreenDestination) },
        navigateToDeeplink = { launcher.launch(deepLinkIntent) },
    )
}
