package cz.applifting.codestinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.applifting.codestinations.ui.theme.CoDestinationsTheme

@Composable
fun Crossroad(
    title: String,
    navigateToBasic: () -> Unit = {},
    navigateToArgs: () -> Unit = {},
    navigateToBackNav: () -> Unit = {},
    navigateToDeeplink: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CrossroadCard(
                text = "Basic navigation",
                onClick = navigateToBasic,
            )
            CrossroadCard(
                text = "Navigation with arguments",
                onClick = navigateToArgs,
            )
            CrossroadCard(
                text = "Back navigation",
                onClick = navigateToBackNav,
            )
            CrossroadCard(
                text = "Deeplink",
                onClick = navigateToDeeplink,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun CrossroadCard(
    text: String,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(16.dp),
        )
    }
}

@Preview
@Composable
private fun CrossroadPreview() {
    CoDestinationsTheme {
        Crossroad(title = "The Epicest Navigation")
    }
}
