package com.kmp.cmp.app.presentation.randomusers

import com.kmp.cmp.app.data.randomusers.common.RandomUserModel

data class RandomUserUiModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val thumbnail: String
)

fun RandomUserModel.toUiModel(): RandomUserUiModel = RandomUserUiModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    thumbnail = thumbnail
)