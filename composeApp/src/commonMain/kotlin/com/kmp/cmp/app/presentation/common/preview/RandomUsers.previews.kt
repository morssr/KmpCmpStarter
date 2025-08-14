package com.kmp.cmp.app.presentation.common.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.cmp.app.presentation.randomusers.RandomUserGridItem
import com.kmp.cmp.app.presentation.randomusers.RandomUsersGrid
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun RandomUsersGridPreview() {
    RandomUsersGrid(
        users = List(10) { PreviewData.getRandomUser(id = it.toString()) },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
@Preview
fun RandomUsersGridItemPreview() {
    RandomUserGridItem(
        user = PreviewData.getRandomUser(id = "1"),
        modifier = Modifier.fillMaxSize()
    )
}
