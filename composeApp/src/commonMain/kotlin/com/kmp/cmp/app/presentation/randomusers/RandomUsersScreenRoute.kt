package com.kmp.cmp.app.presentation.randomusers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.kmp.cmp.app.presentation.common.shimmerBackground
import kmpcmpstarter.composeapp.generated.resources.Res
import kmpcmpstarter.composeapp.generated.resources.add
import kmpcmpstarter.composeapp.generated.resources.cd_add_new_random_users
import kmpcmpstarter.composeapp.generated.resources.cd_back
import kmpcmpstarter.composeapp.generated.resources.cd_delete_all_random_users
import kmpcmpstarter.composeapp.generated.resources.cd_thumbnail
import kmpcmpstarter.composeapp.generated.resources.empty_random_users_state
import kmpcmpstarter.composeapp.generated.resources.error
import kmpcmpstarter.composeapp.generated.resources.error_random_users_state
import kmpcmpstarter.composeapp.generated.resources.random_users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object RandomUsersRoute

fun NavGraphBuilder.randomUsersDestination(
    onNavigateBack: () -> Unit = {}
) {
    composable<RandomUsersRoute> {
        RandomUsersScreenRoute(onNavigateBack = onNavigateBack)
    }
}

@Composable
fun RandomUsersScreenRoute(
    viewModel: RandomUsersViewModel = koinViewModel<RandomUsersViewModel>(),
    onNavigateBack: () -> Unit = {}
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    RandomUsersContent(
        state.value,
        onAddNewRandomUser = { viewModel.fetchNewRandomUser() },
        onDeleteAllUsers = { viewModel.deleteAllRandomUsers() },
        onSnackbarShows = { viewModel.clearErrorMessage() },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomUsersContent(
    state: RandomUsersScreenState,
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onAddNewRandomUser: () -> Unit = {},
    onDeleteAllUsers: () -> Unit = {},
    onSnackbarShows: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    if (state.fetchingErrorMessage.isNotEmpty()) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(state.fetchingErrorMessage)
            onSnackbarShows.invoke()
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.random_users)) },
                navigationIcon = {
                    //Back button
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.cd_back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onDeleteAllUsers) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = stringResource(Res.string.cd_delete_all_random_users)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (state.isFetchingNewUser) {
                FloatingActionButton(onClick = {}) {
                    CircularProgressIndicator()
                }
            } else {
                FloatingActionButton(onClick = onAddNewRandomUser) {
                    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(stringResource(Res.string.add))
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = stringResource(Res.string.cd_add_new_random_users)
                        )
                    }
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val loadState = state.initialLoadState) {
                is InitialLoadState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is InitialLoadState.Success -> {
                    if (state.users.isNotEmpty()) {
                        RandomUsersGrid(
                            users = state.users,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        EmptyUsersState()
                    }
                }

                is InitialLoadState.Error -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("${stringResource(Res.string.error)}: ${loadState.message}")
                    }
                }
            }
        }
    }
}

@Composable
fun RandomUsersGrid(
    users: List<RandomUserUiModel>,
    gridState: LazyGridState = rememberLazyGridState(),
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        state = gridState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        items(users) {
            RandomUserUiModelGridItem(
                it,
                Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun RandomUserUiModelGridItem(
    user: RandomUserUiModel,
    modifier: Modifier = Modifier
) {
    val fullNameAge: String = remember {
        buildString {
            append(user.firstName)
            append(" ")
            append(user.lastName)
            append(" ")
            append(user.age)
        }
    }

    Box(modifier = modifier) {
        val painter = rememberAsyncImagePainter(user.thumbnail)
        val state by painter.state.collectAsState()

        when (state) {
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Loading -> {
                BoxWithConstraints(Modifier.fillMaxSize()) {
                    Spacer(Modifier.size(maxWidth).shimmerBackground())
                }
            }

            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = "$fullNameAge ${stringResource(Res.string.cd_thumbnail)}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            is AsyncImagePainter.State.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(stringResource(Res.string.error_random_users_state))
                }
            }
        }

        Text(
            text = fullNameAge,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .background(Color.Black.copy(alpha = 0.5f))
                .basicMarquee()
        )
    }
}

@Composable
fun EmptyUsersState() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Text(
            text = stringResource(Res.string.empty_random_users_state),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
