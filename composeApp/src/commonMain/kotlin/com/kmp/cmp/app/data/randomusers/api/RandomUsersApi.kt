package com.kmp.cmp.app.data.randomusers.api

import com.kmp.cmp.app.common.utils.Result


interface RandomUsersApi {
    suspend fun fetchRandomUser(request: UserRequest): Result<RandomUserResponse>
}