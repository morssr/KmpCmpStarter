package com.kmp.cmp.app.data.randomusers

import kotlinx.coroutines.flow.Flow
import com.kmp.cmp.app.common.utils.Result
import com.kmp.cmp.app.data.randomusers.common.RandomUserModel

interface RandomUsersRepository {
    suspend fun fetchNewUser(): Result<Unit>
    fun getUsersStream(): Flow<List<RandomUserModel>>
    suspend fun deleteAllUsers()
}
