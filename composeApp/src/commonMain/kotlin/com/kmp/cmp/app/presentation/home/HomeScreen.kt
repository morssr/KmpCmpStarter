package com.kmp.cmp.app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kmpcmpstarter.composeapp.generated.resources.Res
import kmpcmpstarter.composeapp.generated.resources.random_users
import kmpcmpstarter.composeapp.generated.resources.resources
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object HomeScreenRoute

fun NavGraphBuilder.homeScreenDestination(
    onNavigateToResources: () -> Unit = {},
    onNavigateToRandomUsers: () -> Unit = {}
) {
    composable<HomeScreenRoute> {
        HomeScreenRoute(
            modifier = Modifier.safeDrawingPadding(),
            onNavigateToResources = onNavigateToResources,
            onNavigateToRandomUsers = onNavigateToRandomUsers
        )
    }
}

@Composable
private fun HomeScreenRoute(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    modifier: Modifier = Modifier,
    onNavigateToResources: () -> Unit = {},
    onNavigateToRandomUsers: () -> Unit = {}
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        modifier,
        onNavigateToResources = onNavigateToResources,
        onNavigateToRandomUsers = onNavigateToRandomUsers
    )
}

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    onNavigateToResources: () -> Unit = {},
    onNavigateToRandomUsers: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(Modifier.padding(12.dp))

        Button(
            onNavigateToResources,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(stringResource(Res.string.resources))
        }

        Spacer(Modifier.padding(12.dp))

        Button(
            onNavigateToRandomUsers,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(stringResource(Res.string.random_users))
        }
    }
}