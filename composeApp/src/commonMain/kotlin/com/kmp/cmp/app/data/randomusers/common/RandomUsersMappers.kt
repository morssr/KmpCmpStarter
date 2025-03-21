package com.kmp.cmp.app.data.randomusers.common

import com.kmp.cmp.app.data.randomusers.api.RandomUserResponse
import com.kmp.cmp.app.data.randomusers.api.RandomUserResult
import com.kmp.cmp.app.RandomUser
import com.kmp.cmp.app.data.randomusers.storage.RandomUserEntity

fun RandomUserResult.toRandomUserEntity(): RandomUserEntity = RandomUserEntity(
    id = login.uuid,
    firstName = name.first,
    lastName = name.last,
    age = dob.age,
    thumbnail = picture.medium
)

fun RandomUserResponse.toRandomUsersEntities(): List<RandomUserEntity> =
    randomUserResults.map { it.toRandomUserEntity() }

fun RandomUser.dbModelToEntity(): RandomUserEntity = RandomUserEntity(
    id = id,
    firstName = first_name,
    lastName = last_name,
    age = age.toInt(),
    thumbnail = thumbnail
)

fun List<RandomUser>.toRandomUsers(): List<RandomUserEntity> = map { it.dbModelToEntity() }

fun RandomUserEntity.toDbModel(): RandomUser = RandomUser(
    id = id,
    first_name = firstName,
    last_name = lastName,
    age = age.toLong(),
    thumbnail = thumbnail
)

fun RandomUserEntity.toRandomUserModels(): RandomUserModel = RandomUserModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    thumbnail = thumbnail
)

fun List<RandomUserEntity>.toRandomUserModels(): List<RandomUserModel> =
    map { it.toRandomUserModels() }
