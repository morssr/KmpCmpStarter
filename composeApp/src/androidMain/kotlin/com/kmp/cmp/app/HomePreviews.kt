package com.kmp.cmp.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.cmp.app.presentation.home.HomeScreenContent
import com.kmp.cmp.app.presentation.home.HomeScreenState

@Composable
@Preview
fun HomeScreenContentPreview() {
    MaterialTheme {
        HomeScreenContent(
            state = HomeScreenState(),
            modifier = Modifier.fillMaxSize()
        )
    }
}