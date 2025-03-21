package com.kmp.cmp.app.data.randomusers.storage

import kotlinx.coroutines.flow.Flow

interface RandomUsersDao {
    fun getRandomUsersStream(): Flow<List<RandomUserEntity>>
    suspend fun insertRandomUsers(users: List<RandomUserEntity>)
    suspend fun getRandomUserById(id: String): RandomUserEntity?
    suspend fun deleteAllRandomUsers()
}