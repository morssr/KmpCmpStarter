package com.kmp.cmp.app.data.randomusers

import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.kmp.cmp.app.common.utils.Result
import com.kmp.cmp.app.data.randomusers.api.RandomUsersApi
import com.kmp.cmp.app.data.randomusers.api.UserRequest
import com.kmp.cmp.app.data.randomusers.common.RandomUserModel
import com.kmp.cmp.app.data.randomusers.common.toRandomUserModels
import com.kmp.cmp.app.data.randomusers.common.toRandomUsersEntities
import com.kmp.cmp.app.data.randomusers.storage.RandomUsersDao

private const val TAG = "RandomUsersRepositoryImpl"

class RandomUsersRepositoryImpl(
    private val api: RandomUsersApi,
    private val dao: RandomUsersDao,
    logger: Logger
) : RandomUsersRepository {

    private val log = logger.withTag(TAG)

    //TODO add error handling
    override suspend fun fetchNewUser(): Result<Unit> {
        log.d { "fetchNewUser()" }
        val result = when (val userResponse = api.fetchRandomUser(UserRequest())) {
            is Result.Success -> {
                dao.insertRandomUsers(userResponse.data.toRandomUsersEntities())
                Result.Success(Unit)
            }

            is Result.Failure -> {
                log.e { "fetchNewUser() failed with error: ${userResponse.error}" }
                Result.Failure(userResponse.error)
            }
        }
        return result
    }

    override fun getUsersStream(): Flow<List<RandomUserModel>> {
        log.d { "getUsersStream()" }
        return dao.getRandomUsersStream()
            .map { it.toRandomUserModels() }
    }

    override suspend fun deleteAllUsers() {
        log.d { "deleteAllUsers()" }
        dao.deleteAllRandomUsers()
    }
}
