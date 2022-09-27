package cz.applifting.codestinations

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavController

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
            "https://codestinations.applifting.cz/arguments_sub_screen/42?text=This is deeplink, baby! 😎".toUri(),
            context,
            MainActivity::class.java
        )
    }

    Crossroad(
        title = "Compose Navigation",
        navigateToBasic = { navController.navigate("basic") },
        navigateToArgs = { navController.navigate("arguments") },
        navigateToBackNav = { navController.navigate("back") },
        navigateToDeeplink = { launcher.launch(deepLinkIntent) },
    )
}
