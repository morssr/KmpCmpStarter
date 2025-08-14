package com.kmp.cmp.app.presentation.common.preview

import com.kmp.cmp.app.presentation.randomusers.RandomUserUiModel

object PreviewData {

    fun getRandomUser(id: String) = RandomUserUiModel(
        id = id,
        firstName = "John",
        lastName = "Doe",
        age = 30,
        thumbnail = "https://picsum.photos/150"
    )
}