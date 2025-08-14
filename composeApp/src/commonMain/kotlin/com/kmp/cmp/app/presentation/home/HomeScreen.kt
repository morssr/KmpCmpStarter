package com.kmp.cmp.app.presentation.home

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object HomeScreenRoute

fun NavGraphBuilder.homeScreenDestination() {
    composable<HomeScreenRoute> {
        HomeScreenRoute(
            modifier = Modifier.safeDrawingPadding(),
        )
    }
}

@Composable
private fun HomeScreenRoute(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        state = state.value,
        modifier
    )
}

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    modifier: Modifier = Modifier
) {

}