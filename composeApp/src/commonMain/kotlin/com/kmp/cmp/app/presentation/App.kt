package com.kmp.cmp.app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kmp.cmp.app.presentation.common.theme.AppTheme
import com.kmp.cmp.app.presentation.home.HomeScreenRoute
import com.kmp.cmp.app.presentation.home.homeScreenDestination
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppUI() {
    AppTheme {
        val navController = rememberNavController()

        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = HomeScreenRoute,
            ) {
                homeScreenDestination()
            }
        }
    }
}