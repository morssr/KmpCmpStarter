package com.kmp.cmp.app.presentation.resources

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kmp.cmp.app.Greeting
import kmpcmpstarter.composeapp.generated.resources.Res
import kmpcmpstarter.composeapp.generated.resources.click_me
import kmpcmpstarter.composeapp.generated.resources.compose_multiplatform
import kmpcmpstarter.composeapp.generated.resources.hello_world
import kmpcmpstarter.composeapp.generated.resources.resources
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Serializable
object ResourcesScreenRoute

fun NavGraphBuilder.resourcesDestination(
    onNavigateBack: () -> Unit = {}
) {
    composable<ResourcesScreenRoute> { ResourcesScreen(onNavigateBack) }
}

@Composable
fun ResourcesScreen(onNavigateBack: () -> Unit = {}) {
    ResourcesContent(onNavigateBack = onNavigateBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesContent(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.resources)) },
                navigationIcon = {
                    //Back button
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        var showContent by remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Button(onClick = {
                showContent = !showContent
            }) {
                Text(stringResource(Res.string.click_me))
            }

            Spacer(Modifier.padding(16.dp))
            Text(stringResource(Res.string.hello_world))
            Spacer(Modifier.padding(16.dp))
            Text(
                text = stringResource(Res.string.hello_world),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.padding(16.dp))
            Text(
                text = stringResource(Res.string.hello_world),
                style = MaterialTheme.typography.headlineLarge
            )

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("${stringResource(Res.string.click_me)} $greeting")
                }
            }
        }
    }
}
