package com.kmp.cmp.app.domain.randomusers

import co.touchlab.kermit.Logger
import com.kmp.cmp.app.data.randomusers.RandomUsersRepository
import com.kmp.cmp.app.data.randomusers.common.RandomUserModel
import kotlinx.coroutines.flow.Flow

private const val TAG = "GetAllRandomUsersUseCase"

class GetAllRandomUsersUseCase(
    private val randomUsersRepository: RandomUsersRepository,
    logger: Logger
) {
    private val log = logger.withTag(TAG)

    operator fun invoke(): Flow<List<RandomUserModel>> {
        log.d { "invoke(): called" }
        return randomUsersRepository.getUsersStream()
    }
}