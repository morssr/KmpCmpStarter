package com.kmp.cmp.app.data.randomusers.api

import co.touchlab.kermit.Logger
import com.kmp.cmp.app.data.common.NetworkConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.kmp.cmp.app.common.utils.Result

private const val TAG = "RandomUsersApiImpl"

class RandomUsersApiImpl(
    private val client: HttpClient,
    logger: Logger
) : RandomUsersApi {

    private val log = logger.withTag(TAG)

    override suspend fun fetchRandomUser(request: UserRequest): Result<RandomUserResponse> {
        log.d { "fetchRandomUser(): $request" }
        try {
            val response = client.get(NetworkConstants.RANDOM_USER_URL)
            val randomUserResponse = response.body<RandomUserResponse>()
            return Result.Success(randomUserResponse)
        } catch (e: Exception) {
            log.e(e) { "fetchRandomUser(): request failed with error: $e" }
            return Result.Failure(e)
        }
    }
}